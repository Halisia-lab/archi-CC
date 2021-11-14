import domain.model.*;
import domain.repository.MemberRepository;
import domain.service.MemberApplicationService;
import domain.service.PaymentService;
import infrastructure.InMemoryMemberRepository;

import java.time.LocalDate;

public class App 
{
    public static void main( String[] args )
    {
        Address address = Address.of("12bis", "avenue de la paix", "92220", "Bagneux", "France");
        CreateMember createMember = new CreateMember("halifa", "halisia", "halifa.halisia@gmail.com", "mypswd", address);
        MemberRepository memberRepository = new InMemoryMemberRepository();
        MemberApplicationService memberApplicationService = new MemberApplicationService(memberRepository);
        Member member = null;

        try {
            MemberId memberId = memberApplicationService.apply(createMember);
            member = memberRepository.findById(memberId);
        } catch (Exception e) {
            System.out.println("ERROR DURING MEMBER APPLICATION : " + e.getMessage());
        }

        CreditCard creditCard = CreditCard.of("Halifa Halisia", "158215811568", LocalDate.of(2024,7,1));
        if(member != null) {
            member.addCreditCard(creditCard);
        }

        try {
            PaymentService paymentService = new PaymentService();
            paymentService.processPaymentFor(member);
        } catch(Exception e) {
            System.out.println("ERROR DURING PAYMENT PROCESS : " + e.getMessage());
        }
    }
}
