package use_cases.assign_tradesman.application.request_verification;

import kernel.ApplicationEvent;
import use_cases.assign_tradesman.domain.AssignRequestID;

public class VerifyAssignRequestApplicationEvent implements ApplicationEvent {
    private final AssignRequestID assignRequestID;

    public VerifyAssignRequestApplicationEvent(AssignRequestID assignRequestID) {
        this.assignRequestID = assignRequestID;
    }
}
