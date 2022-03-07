
import domain.MemberId;
import domain.Role;
import kernel.Event;
import kernel.EventDispatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_cases.add_member.application.member.AddressDTO;
import use_cases.add_member.application.member.CreateContractor;
import use_cases.add_member.application.member.CreateMemberCommandHandler;
import use_cases.add_member.application.member_verification.VerifyMemberApplicationHandler;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.application.payment.CreatePaymentCommandHandler;
import use_cases.add_member.application.payment.RecurringPaymentUtils;
import use_cases.add_member.application.payment_verification.VerifyPaymentApplicationHandler;
import use_cases.add_member.domain.CreditCard;
import use_cases.add_member.infrastructure.InMemoryMemberRepository;
import use_cases.add_member.infrastructure.InMemoryPaymentRepository;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberTest {

    ScheduledExecutorService executorService;
    EventDispatcher<Event> eventEventDispatcher;
    InMemoryMemberRepository memberRepository;
    InMemoryPaymentRepository paymentRepository;
    VerifyMemberApplicationHandler verifyMemberApplicationHandler;
    VerifyPaymentApplicationHandler verifyPaymentApplicationHandler;
    CreateMemberCommandHandler memberCommandHandler;
    CreatePaymentCommandHandler paymentCommandHandler;


    @Before
    public void setUp() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        eventEventDispatcher = event -> System.out.println("Dispatching Event " + event.getClass().getName());

        memberRepository = new InMemoryMemberRepository();
        paymentRepository = new InMemoryPaymentRepository();
        verifyMemberApplicationHandler = new VerifyMemberApplicationHandler(memberRepository, eventEventDispatcher);
        verifyPaymentApplicationHandler = new VerifyPaymentApplicationHandler(eventEventDispatcher);
        memberCommandHandler = new CreateMemberCommandHandler(memberRepository, eventEventDispatcher, verifyMemberApplicationHandler);
        paymentCommandHandler = new CreatePaymentCommandHandler(paymentRepository, eventEventDispatcher, verifyPaymentApplicationHandler);

    }


    @Test
    public void member_handle_on_valid_user_should_return_member_id() {

        // 2 - Create a member
        CreateContractor memberHalisia = new CreateContractor("Halifa", "Halisia", "halifa.halisia@gmail.com", "mypswd", new AddressDTO("12 Avenue de la paix", "BAGNEUX"), Role.CONTRACTOR);

        // 3 - Create a payment
        CreatePayment createPayment = new CreatePayment(20, memberHalisia, CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024, 7, 1)));

        // 4 - Proceed monthly payment
        paymentCommandHandler.handle(createPayment);
        executorService.scheduleAtFixedRate(() -> {
            paymentCommandHandler.handle(createPayment);
        }, RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);

        //5 - Member Application validation and finalization
        final MemberId memberId = memberCommandHandler.handle(memberHalisia);


        Assert.assertNotNull(memberId);


    }

    @Test
    public void member_handle_on_invalid_user_should_not_return_member_id() {

        // 2 - Create a member
        CreateContractor memberHalisia = new CreateContractor("Halifa", "Halisia", "email", "mypswd", new AddressDTO("12 Avenue de la paix", "BAGNEUX"), Role.CONTRACTOR);

        // 3 - Create a payment
        CreatePayment createPayment = new CreatePayment(20, memberHalisia, CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024, 7, 1)));

        // 4 - Proceed monthly payment
        paymentCommandHandler.handle(createPayment);
        executorService.scheduleAtFixedRate(() -> {
            paymentCommandHandler.handle(createPayment);
        }, RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);

        //5 - Member Application validation and finalization
        final MemberId memberId = memberCommandHandler.handle(memberHalisia);


        Assert.assertNull(memberId);


    }


    @Test
    public void member_application_with_already_used_email_should_throw_NotValidApplicationException() {

        //  Create a member
        CreateContractor memberHalisia = new CreateContractor("Halifa", "Halisia", "halifa.halisia@gmail.com", "mypswd", new AddressDTO("12 Avenue de la paix", "BAGNEUX"), Role.CONTRACTOR);
        CreateContractor memberLucas = new CreateContractor("Rochette", "Lucas", "halifa.halisia@gmail.com", "mypswd", new AddressDTO("15 rue de Paris", "PARIS"), Role.CONTRACTOR);

        // Create a payment
        CreatePayment createPaymentHalisia = new CreatePayment(20, memberHalisia, CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024, 7, 1)));
        CreatePayment createPaymentLucas = new CreatePayment(20, memberHalisia, CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024, 7, 1)));

        // Proceed monthly payment
        paymentCommandHandler.handle(createPaymentHalisia);
        executorService.scheduleAtFixedRate(() -> {
            paymentCommandHandler.handle(createPaymentHalisia);
        }, RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);
        // Proceed monthly payment
        paymentCommandHandler.handle(createPaymentLucas);
        executorService.scheduleAtFixedRate(() -> {
            paymentCommandHandler.handle(createPaymentLucas);
        }, RecurringPaymentUtils.DAYS_UNTIL_NEXT_MONTH, RecurringPaymentUtils.DAYS_IN_CURRENT_MONTH, TimeUnit.DAYS);

        // Member Application validation and finalization
        final MemberId memberIdHalisia = memberCommandHandler.handle(memberHalisia);
        final MemberId memberIdLucas = memberCommandHandler.handle(memberLucas);

        Assert.assertNull(memberIdLucas);



    }

}