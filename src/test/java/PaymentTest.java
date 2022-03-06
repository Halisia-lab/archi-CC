import domain.exception.NotValidCreditCardException;
import domain.model.*;
import domain.repository.MemberRepository;
import domain.service.MemberApplicationService;
import domain.service.PaymentService;
import infrastructure.InMemoryMemberRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class PaymentTest {

    private Member member;

    @Before
    public void setUp()  {
        Address address = Address.of("12bis", "avenue de la paix", "92220", "Bagneux", "France");
        CreateMember createMember = new CreateMember("halifa", "halisia", "halifa.halisia@gmail.com", "mypswd", address);
        MemberRepository memberRepository = new InMemoryMemberRepository();
        MemberApplicationService memberApplicationService = new MemberApplicationService(memberRepository);
        MemberId memberId = memberApplicationService.apply(createMember);
        member = memberRepository.findById(memberId);

    }


    @Test
    public void payment_without_credit_card_should_throw_NotValidCreditCardException()
    {

        PaymentService paymentService = new PaymentService();

        assertThrows(NotValidCreditCardException.class,
                () -> paymentService.processPaymentFor(member));
    }


    @Test
    public void payment_with_valid_credit_card_should_not_throw()
    {
        CreditCard creditCard = CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024,7,1));
        member.addCreditCard(creditCard);

        PaymentService paymentService = new PaymentService();

        assertDoesNotThrow(() -> paymentService.processPaymentFor(member));
    }


    @Test
    public void payment_with_expired_credit_card_should_throw_NotValidCreditCardException()
    {

        CreditCard creditCard = CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2020,7,1));
        member.addCreditCard(creditCard);

        PaymentService paymentService = new PaymentService();

        assertThrows(NotValidCreditCardException.class,
                () -> paymentService.processPaymentFor(member));
    }

    @Test
    public void payment_with_incorrect_credit_card_number_should_throw_NotValidCreditCardException()
    {

        CreditCard creditCard = CreditCard.of("Halifa Halisia", "12345678910", LocalDate.of(2026,1,23));
        member.addCreditCard(creditCard);

        PaymentService paymentService = new PaymentService();

        assertThrows(NotValidCreditCardException.class,
                () -> paymentService.processPaymentFor(member));
    }




}
