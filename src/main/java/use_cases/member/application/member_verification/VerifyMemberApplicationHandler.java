package use_cases.member.application.member_verification;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.member.domain.MemberEventSourcedRepository;

public class VerifyMemberApplicationHandler implements CommandHandler<VerifyMemberApplication, Void> {
    private final MemberEventSourcedRepository memberEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public VerifyMemberApplicationHandler(MemberEventSourcedRepository memberEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public Void handle(VerifyMemberApplication command) {
        VerifyMemberFullname verifyMemberFullname = new VerifyMemberFullname();
        verifyMemberFullname.validate(command.createMember);

        VerifyMemberEmail verifyMemberEmail = new VerifyMemberEmail(memberEventSourcedRepository);
        verifyMemberEmail.validate(command.createMember.email);

        VerifyMemberPassword verifyMemberPassword = new VerifyMemberPassword();
        verifyMemberPassword.validate(command.createMember.password);

        VerifyMemberPayment verifyMemberPayment = new VerifyMemberPayment(command.createMember);
        verifyMemberPayment.validate(command.createMember.createPayment);
        eventEventDispatcher.dispatch(new VerifyMemberApplicationEvent(command.memberId));
        return null;
    }


}
