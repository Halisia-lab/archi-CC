import kernel.Event;
import kernel.EventDispatcher;
import use_cases.member.application.member.AddressDTO;
import use_cases.member.application.member.CreateMember;
import use_cases.member.application.member.CreateMemberCommandHandler;
import use_cases.member.application.payment.CreatePayment;
import use_cases.member.application.payment.CreatePaymentCommandHandler;
import use_cases.member.application.member_verification.VerifyMemberApplicationHandler;
import use_cases.member.application.payment.RecurringPaymentUtils;
import use_cases.member.application.payment_verification.VerifyPaymentApplicationHandler;
import use_cases.member.domain.*;
import use_cases.member.infrastructure.InMemoryMemberRepository;
import use_cases.member.infrastructure.InMemoryPaymentRepository;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {

        // 1- Initializations
        ScheduledExecutorService executorService;
        executorService = Executors.newSingleThreadScheduledExecutor();
        EventDispatcher<Event> eventEventDispatcher = event -> System.out.println("Dispatching Event " + event.getClass().getName());
        final InMemoryMemberRepository memberRepository = new InMemoryMemberRepository();
        final InMemoryPaymentRepository paymentRepository = new InMemoryPaymentRepository();
        VerifyMemberApplicationHandler verifyMemberApplicationHandler = new VerifyMemberApplicationHandler(memberRepository, eventEventDispatcher);
        VerifyPaymentApplicationHandler verifyPaymentApplicationHandler = new VerifyPaymentApplicationHandler(eventEventDispatcher);

        // 2 - Create member
        CreateMemberCommandHandler memberCommandHandler = new CreateMemberCommandHandler(memberRepository, eventEventDispatcher, verifyMemberApplicationHandler);
        CreateMember createMember = new CreateMember("Halifa", "Halisia", "halifa.halisia@gmail.com", "mypswd", new AddressDTO("12 Avenue de la paix", "BAGNEUX"));

        // 3 - Create payment
        CreatePaymentCommandHandler paymentCommandHandler = new CreatePaymentCommandHandler(paymentRepository, eventEventDispatcher, verifyPaymentApplicationHandler);
        CreatePayment createPayment = new CreatePayment(20, createMember, CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024, 7, 1)));

        // 4 - Proceed monthly payment
        paymentCommandHandler.handle(createPayment);
        executorService.scheduleAtFixedRate(() -> {
            try {
                paymentCommandHandler.handle(createPayment);
            } catch (Exception e) {
                System.out.println("ERROR DURING PAYMENT PROCESS : " + e.getMessage());
            }
        }, RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);

        //5 - Member Application finalization
        try {
            final MemberId memberId = memberCommandHandler.handle(createMember);
        } catch(Exception e) {
            System.out.println("ERROR DURING MEMBER APPLICATION : " + e.getMessage());
        }
    }
}
