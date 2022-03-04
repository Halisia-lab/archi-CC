package use_cases.request_tradesman.application.request_verification;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.add_member.domain.MemberEventSourcedRepository;

public class VerifyRequestApplicationHandler implements CommandHandler<VerifyRequestApplication, Void> {
    private final MemberEventSourcedRepository memberEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public VerifyRequestApplicationHandler(MemberEventSourcedRepository memberEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public Void handle(VerifyRequestApplication command) {
        VerifyRequestProject verifyRequestProject = new VerifyRequestProject();
        verifyRequestProject.validate(command.createRequest);
        VerifyRequestContractor verifyRequestContractor = new VerifyRequestContractor(memberEventSourcedRepository);
        verifyRequestContractor.validate(command.createRequest);

        eventEventDispatcher.dispatch(new VerifyRequestApplicationEvent(command.requestId));
        return null;
    }
}
