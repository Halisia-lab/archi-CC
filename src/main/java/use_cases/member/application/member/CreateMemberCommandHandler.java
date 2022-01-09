package use_cases.member.application.member;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.member.application.member_verification.VerifyMemberApplication;
import use_cases.member.application.member_verification.VerifyMemberApplicationHandler;
import use_cases.member.domain.*;

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
        Member member = Member.of(memberId, createMember.lastname, createMember.firstname, createMember.email, createMember.password, new Address(createMember.address.street, createMember.address.city));
        verifyMemberApplicationHandler.handle(new VerifyMemberApplication(memberId,createMember));
        memberEventSourcedRepository.add(member);
        eventEventDispatcher.dispatch(new CreateMemberApplicationEvent(memberId));
        return memberId;
    }
}
