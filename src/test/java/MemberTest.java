import domain.exception.NotValidApplicationException;
import domain.exception.NotValidCreditCardException;
import domain.model.Address;
import domain.model.CreateMember;
import domain.model.Member;
import domain.model.MemberId;
import domain.repository.MemberRepository;
import domain.service.MemberApplicationService;
import infrastructure.InMemoryMemberRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberTest {

    MemberRepository memberRepository;
    MemberApplicationService memberApplicationService;


    @Before
    public void setUp()  {
        memberRepository = new InMemoryMemberRepository();
        memberApplicationService = new MemberApplicationService(memberRepository);
    }

    @Test
    public void valid_user_verification_should_not_throw()
    {

        Address address = Address.of("12bis", "avenue de la paix", "92220", "Bagneux", "France");
        CreateMember createMember = new CreateMember("halifa", "halisia", "halifa.halisia@gmail.com", "mypswd", address);
        MemberId id = memberRepository.nextIdentity();

        Member member = Member.of(id,createMember.lastname, createMember.firstname, createMember.email, createMember.password, createMember.address);


        assertDoesNotThrow(() -> memberApplicationService.verifyApplicationOf(member));


    }

    @Test
    public void invalid_user_verification_should_throw_NotValidApplicationException()
    {

        Address address = Address.of("12bis", "avenue de la paix", "92220", "Bagneux", "France");
        CreateMember createMember = new CreateMember("halifa", "halisia", "invalidEmail", "mypswd", address);
        MemberId id = memberRepository.nextIdentity();

        Member member = Member.of(id,createMember.lastname, createMember.firstname, createMember.email, createMember.password, createMember.address);



        assertThrows(NotValidApplicationException.class,
                () -> memberApplicationService.verifyApplicationOf(member));


    }


    @Test
    public void user_application_with_already_used_email_should_throw_NotValidApplicationException()
    {

        Address address = Address.of("12bis", "avenue de la paix", "92220", "Bagneux", "France");
        CreateMember createMember = new CreateMember("halifa", "halisia", "halifa.halisia@gmail.com", "mypswd", address);
        memberApplicationService.apply(createMember);

        CreateMember createMemberWithSameEmail = new CreateMember("John", "Coffee", "halifa.halisia@gmail.com", "pwdpwdpwd", address);
        
        assertThrows(NotValidApplicationException.class,
                () -> memberApplicationService.apply(createMemberWithSameEmail));
    }
}
