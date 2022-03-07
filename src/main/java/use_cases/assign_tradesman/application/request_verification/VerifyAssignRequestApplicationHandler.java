package use_cases.assign_tradesman.application.request_verification;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import domain.MemberEventSourcedRepository;

public class VerifyAssignRequestApplicationHandler implements CommandHandler<VerifyAssignRequestApplication, Void> {
    private final MemberEventSourcedRepository memberEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public VerifyAssignRequestApplicationHandler(MemberEventSourcedRepository memberEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public Void handle(VerifyAssignRequestApplication command) {
        VerifyAssignRequestProject verifyAssignRequestProject = new VerifyAssignRequestProject();
        verifyAssignRequestProject.validate(command.createAssignRequest);
        VerifyAssignRequestTradesman verifyAssignRequestTradesman = new VerifyAssignRequestTradesman(memberEventSourcedRepository);
        verifyAssignRequestTradesman.validate(command.createAssignRequest);
        VerifyAssignRequestContractor verifyAssignRequestContractor = new VerifyAssignRequestContractor(memberEventSourcedRepository);
        verifyAssignRequestContractor.validate(command.createAssignRequest);

        eventEventDispatcher.dispatch(new VerifyAssignRequestApplicationEvent(command.assignRequestID));
        return null;
    }
}
