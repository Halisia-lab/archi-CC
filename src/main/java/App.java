import domain.model.Address;
import domain.model.CreateMember;
import domain.repository.MemberRepository;
import domain.service.MemberApplicationService;
import infrastructure.InMemoryMemberRepository;

/**
 * Apply for membership
 * Verify Application
 * Error
 * Process payment
 * Approve tradesman/contractor
 */
public class App 
{
    public static void main( String[] args )
    {
        Address address = Address.of("12bis", "avenue de la paix", "92220", "Bagneux", "France");
        CreateMember createMember = new CreateMember("halifa", "halisia", "halifa.halisia@gmail.com", "mypswd", address);
        MemberRepository memberRepository = new InMemoryMemberRepository();
        MemberApplicationService memberApplicationService = new MemberApplicationService(memberRepository);
        memberApplicationService.apply(createMember);
    }
}
