import kernel.Event;
import kernel.EventDispatcher;
import use_cases.add_member.application.member.AddressDTO;
import use_cases.add_member.application.member.CreateMember;
import use_cases.add_member.application.member.CreateMemberCommandHandler;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.application.payment.CreatePaymentCommandHandler;
import use_cases.add_member.application.member_verification.VerifyMemberApplicationHandler;
import use_cases.add_member.application.payment.RecurringPaymentUtils;
import use_cases.add_member.application.payment_verification.VerifyPaymentApplicationHandler;
import use_cases.add_member.domain.*;
import use_cases.add_member.infrastructure.InMemoryMemberRepository;
import use_cases.add_member.infrastructure.InMemoryPaymentRepository;
import use_cases.request_trademan.application.request.CreateRequest;
import use_cases.request_trademan.application.request.CreateRequestCommandHandler;
import use_cases.request_trademan.application.request_verification.VerifyRequestApplicationHandler;
import use_cases.request_trademan.domain.Project;
import use_cases.request_trademan.domain.ProjectLocalization;
import use_cases.request_trademan.domain.ProjectSkills;
import use_cases.request_trademan.infrastructure.InMemoryRequestRepository;

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
        final InMemoryMemberRepository memberRepository = new InMemoryMemberRepository();
        final InMemoryPaymentRepository paymentRepository = new InMemoryPaymentRepository();
        VerifyMemberApplicationHandler verifyMemberApplicationHandler = new VerifyMemberApplicationHandler(memberRepository, eventEventDispatcher);
        VerifyPaymentApplicationHandler verifyPaymentApplicationHandler = new VerifyPaymentApplicationHandler(eventEventDispatcher);

        // 2 - Create member
        CreateMemberCommandHandler memberCommandHandler = new CreateMemberCommandHandler(memberRepository, eventEventDispatcher, verifyMemberApplicationHandler);
        CreateMember createMember = new CreateMember("Halifa", "Halisia", "halifa.halisia@gmail.com", "mypswd", new AddressDTO("12 Avenue de la paix", "BAGNEUX"), Role.CONTRACTOR);

        // 3 - Create payment
        CreatePaymentCommandHandler paymentCommandHandler = new CreatePaymentCommandHandler(paymentRepository, eventEventDispatcher, verifyPaymentApplicationHandler);
        CreatePayment createPayment = new CreatePayment(20, createMember, CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024, 7, 1)));

        // 4 - Proceed monthly payment
        paymentCommandHandler.handle(createPayment);
        executorService.scheduleAtFixedRate(() -> {
                paymentCommandHandler.handle(createPayment);
        }, RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);

        //5 - Member Application finalization
            final MemberId memberId = memberCommandHandler.handle(createMember);


        /*
        USE CASE REQUEST TRADEMAN
         */

        //1 - Initializations
        final InMemoryRequestRepository requestRepository = new InMemoryRequestRepository();
        VerifyRequestApplicationHandler verifyRequestApplicationHandler = new VerifyRequestApplicationHandler(memberRepository, eventEventDispatcher);

        //2- Create request
        CreateRequestCommandHandler requestCommandHandler = new CreateRequestCommandHandler(requestRepository, eventEventDispatcher, verifyRequestApplicationHandler);
        CreateRequest createRequest = new CreateRequest(new Project("project1", Duration.ofDays(30), 1000, ProjectLocalization.ILE_DE_FRANCE, List.of(ProjectSkills.BUILDING_KNOWLEDGE, ProjectSkills.MATHS_SKILLS), List.of("job1")), memberId);

        //3- Request Trademan finalization
        requestCommandHandler.handle(createRequest);

        //4- Match Trademan



    }
}
