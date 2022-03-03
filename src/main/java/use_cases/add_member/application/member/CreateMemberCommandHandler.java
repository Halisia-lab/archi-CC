package use_cases.add_member.application.member;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.add_member.application.member_verification.VerifyMemberApplication;
import use_cases.add_member.application.member_verification.VerifyMemberApplicationHandler;
import use_cases.add_member.domain.*;

public class CreateMemberCommandHandler implements CommandHandler<CreateMember, MemberId> {
    private final MemberEventSourcedRepository memberEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final VerifyMemberApplicationHandler verifyMemberApplicationHandler;

    public CreateMemberCommandHandler(MemberEventSourcedRepository memberEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher, VerifyMemberApplicationHandler verifyMemberApplicationHandler) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.verifyMemberApplicationHandler = verifyMemberApplicationHandler;
    }

    @Override
    public MemberId handle(CreateMember createMember) {
        final MemberId memberId = memberEventSourcedRepository.nextIdentity();
        Member member = Member.of(memberId, createMember.lastname, createMember.firstname, createMember.email, createMember.password, new Address(createMember.address.street, createMember.address.city), createMember.role);
        try {
            verifyMemberApplicationHandler.handle(new VerifyMemberApplication(memberId,createMember));
            memberEventSourcedRepository.add(member);
            eventEventDispatcher.dispatch(new CreateMemberApplicationEvent(memberId));
            return memberId;
        } catch (Exception e) {
            System.out.println("ERROR DURING MEMBER APPLICATION : " + e.getMessage());
        }
        return null;
    }
}
