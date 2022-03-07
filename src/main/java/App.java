import domain.*;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.add_member.application.member.AddressDTO;
import use_cases.add_member.application.member.CreateMemberCommandHandler;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.application.payment.CreatePaymentCommandHandler;
import use_cases.add_member.application.member_verification.VerifyMemberApplicationHandler;
import use_cases.add_member.application.payment.RecurringPaymentUtils;
import use_cases.add_member.application.payment_verification.VerifyPaymentApplicationHandler;
import use_cases.add_member.domain.*;
import use_cases.add_member.infrastructure.InMemoryMemberRepository;
import use_cases.add_member.infrastructure.InMemoryPaymentRepository;
import use_cases.assign_tradesman.application.request_verification.VerifyAssignRequestApplicationHandler;
import use_cases.assign_tradesman.domain.AssignTradesmanService;
import use_cases.assign_tradesman.domain.Booking;
import use_cases.assign_tradesman.domain.BookingId;
import use_cases.assign_tradesman.infrastructure.InMemoryAssignRequestRepository;
import use_cases.assign_tradesman.infrastructure.InMemoryBookingRepository;
import use_cases.match_tradesman.domain.MatchTradesManEngine;
import use_cases.add_member.application.member.CreateContractor;
import use_cases.add_member.application.member.CreateTradesman;
import use_cases.request_tradesman.application.request.CreateRequest;
import use_cases.request_tradesman.application.request.CreateRequestCommandHandler;
import use_cases.request_tradesman.application.request_verification.VerifyRequestApplicationHandler;
import use_cases.request_tradesman.domain.*;
import use_cases.request_tradesman.infrastructure.InMemoryRequestRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {

        ScheduledExecutorService executorService;
        executorService = Executors.newSingleThreadScheduledExecutor();
        EventDispatcher<Event> eventEventDispatcher = event -> System.out.println("Dispatching Event " + event.getClass().getName());

        /*
        USE CASE ADD_MEMBER
         */
        // 1- Initializations
        System.out.println("\n*******USE_CASE ADD_MEMBER********");
        final InMemoryMemberRepository memberRepository = new InMemoryMemberRepository();
        final InMemoryPaymentRepository paymentRepository = new InMemoryPaymentRepository();
        VerifyMemberApplicationHandler verifyMemberApplicationHandler = new VerifyMemberApplicationHandler(memberRepository, eventEventDispatcher);
        VerifyPaymentApplicationHandler verifyPaymentApplicationHandler = new VerifyPaymentApplicationHandler(eventEventDispatcher);

        // 2 - Create a member
        CreateMemberCommandHandler memberCommandHandler = new CreateMemberCommandHandler(memberRepository, eventEventDispatcher, verifyMemberApplicationHandler);
        CreateContractor memberHalisia = new CreateContractor("Halifa", "Halisia", "halifa.halisia@gmail.com", "mypswd", new AddressDTO("12 Avenue de la paix", "BAGNEUX"), Role.CONTRACTOR);

        // 3 - Create a payment
        CreatePaymentCommandHandler paymentCommandHandler = new CreatePaymentCommandHandler(paymentRepository, eventEventDispatcher, verifyPaymentApplicationHandler);
        CreatePayment createPayment = new CreatePayment(20, memberHalisia, CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024, 7, 1)));

        // 4 - Proceed monthly payment
        paymentCommandHandler.handle(createPayment);
        executorService.scheduleAtFixedRate(() -> paymentCommandHandler.handle(createPayment), RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);

        //5 - Member Application validation and finalization
        final MemberId memberId = memberCommandHandler.handle(memberHalisia);


        /*
        USE CASE REQUEST TRADEMAN
         */
        //1 - Initializations and tradesmen creation
        System.out.println("\n*******USE_CASE REQUEST TRADESMAN********");
        final InMemoryRequestRepository requestRepository = new InMemoryRequestRepository();
        VerifyRequestApplicationHandler verifyRequestApplicationHandler = new VerifyRequestApplicationHandler(memberRepository, eventEventDispatcher);

        System.out.println("\n* Create some tradesmen *");
        CreateTradesman tradesManInes = new CreateTradesman("Ines", "Sacramento", "ines.sacramento@gmail.com", "mypswd", new AddressDTO("12 Avenue de la paix", "PARIS"), List.of(ProjectSkills.BUILDING_KNOWLEDGE, ProjectSkills.MATHS_SKILLS), null, 200, Role.TRADESMAN);
        CreatePayment paymentInes = new CreatePayment(20, tradesManInes, CreditCard.of("Ines Sacramento", "158255811568", LocalDate.of(2024, 7, 1)));
        paymentCommandHandler.handle(paymentInes);
        executorService.scheduleAtFixedRate(() -> paymentCommandHandler.handle(createPayment), RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);
        final MemberId idInes = memberCommandHandler.handle(tradesManInes);

        CreateTradesman tradesManLucas = new CreateTradesman("Lucas", "Rochette", "lucas.rochette@gmail.com", "mypswd", new AddressDTO("12 Avenue de la paix", "MARSEILLE"), List.of(ProjectSkills.COORDINATION), List.of(ProjectLocalization.AUVERGNE_RHONE_ALPES), 300, Role.TRADESMAN);
        CreatePayment paymentLucas = new CreatePayment(20, tradesManLucas, CreditCard.of("Lucas Rochette", "158255813568", LocalDate.of(2024, 7, 1)));
        paymentCommandHandler.handle(paymentLucas);
        executorService.scheduleAtFixedRate(() -> paymentCommandHandler.handle(createPayment), RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);
        final MemberId idLucas = memberCommandHandler.handle(tradesManLucas);

        System.out.println();
        //2- Create a tradesmen request
        CreateRequestCommandHandler requestCommandHandler = new CreateRequestCommandHandler(requestRepository, eventEventDispatcher, verifyRequestApplicationHandler);

        Project project = new Project("project1", LocalDate.of(2023,1,1), Duration.ofDays(30), 1000, ProjectLocalization.ILE_DE_FRANCE, List.of(ProjectSkills.BUILDING_KNOWLEDGE, ProjectSkills.MATHS_SKILLS), List.of("job1"));
        CreateRequest createRequest = new CreateRequest(project, memberId);

        //3- Request validation and finalization (ready to match tradesman)
        final RequestId requestId = requestCommandHandler.handle(createRequest);
        final Request request = requestRepository.findById(requestId);


        /*
        USE CASE MATCH TRADESMAN
         */
        //1- Initializations
        System.out.println("\n*******USE_CASE MATCH TRADESMAN********");

        //2- Create a matchTradesman Engine with the previous Request
        MatchTradesManEngine match = new MatchTradesManEngine(requestRepository, memberRepository, requestId);

        //3 search tradesman candidates
        match.searchCandidates();

        //4 check best fit candidate
        Tradesman predictedBestFitCandidate = (Tradesman) memberRepository.findById(idInes);
        System.out.println(match.test(predictedBestFitCandidate));

        /*
        USE CASE ASSIGN TRADESMAN
         */

        //1- Initializations
        System.out.println("\n*******USE_CASE ASSIGN TRADESMAN********");
        InMemoryBookingRepository bookingRepository = new InMemoryBookingRepository();
        InMemoryAssignRequestRepository assignRequestRepository = new InMemoryAssignRequestRepository();
        VerifyAssignRequestApplicationHandler verifyAssignRequestApplicationHandler = new VerifyAssignRequestApplicationHandler(memberRepository, eventEventDispatcher);
        AssignTradesmanService assignTradesmanService = new AssignTradesmanService(assignRequestRepository, eventEventDispatcher, verifyAssignRequestApplicationHandler, bookingRepository);
        BookingId bookingId = assignTradesmanService.doAssignTradesman(predictedBestFitCandidate.id(), memberId, project);

        // TEST
        List<Booking> bestFitTradesmanBookings = bookingRepository.findAllByTradesmanId(predictedBestFitCandidate.id());
        System.out.println(bestFitTradesmanBookings.size());
        for (Booking booking : bestFitTradesmanBookings){
            System.out.println(booking.toString());
        }

        BookingId bookingId2 = assignTradesmanService.doAssignTradesman(predictedBestFitCandidate.id(), memberId,
                new Project("project1", LocalDate.of(2022,12,30), Duration.ofDays(30), 1000, ProjectLocalization.ILE_DE_FRANCE, List.of(ProjectSkills.BUILDING_KNOWLEDGE, ProjectSkills.MATHS_SKILLS), List.of("job1")));

        for (Booking booking : bestFitTradesmanBookings){
            System.out.println(booking.toString());
        }
    }
}
