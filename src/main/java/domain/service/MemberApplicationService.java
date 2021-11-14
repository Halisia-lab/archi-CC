package domain.service;

import domain.model.CreateMember;
import domain.model.Member;
import domain.model.MemberId;
import domain.model.NotValidApplicationException;
import domain.repository.MemberRepository;

public class MemberApplicationService {

    public static final int PASSWORD_MIN_LENGTH = 6;
    private final MemberRepository memberRepository;

    public MemberApplicationService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void apply(CreateMember createMember) {
        final MemberId id = memberRepository.nextIdentity();
        Member member = Member.of(id,createMember.lastname, createMember.firstname, createMember.email, createMember.password, createMember.address);
        verifyApplicationOf(member);
        memberRepository.add(member);

        System.out.println("Application of Member with Id " + id.getValue());
    }

    public void verifyApplicationOf(Member member) {
        EmailValidatorService emailValidatorService = new EmailValidatorService();
        if(member.getFirstname().equals("") || member.getLastname().equals("")) {
            throw NotValidApplicationException.emptyName();
        }
        if(member.getPassword().length() < PASSWORD_MIN_LENGTH) {
            throw NotValidApplicationException.tooShortPassword();
        }
        if(!emailValidatorService.validate(member.getEmail())) {
            throw NotValidApplicationException.incorrectEmail();
        }
        if(memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw NotValidApplicationException.emailAlreadyExists();
        }


    }

}
