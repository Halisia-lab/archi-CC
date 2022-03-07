package use_cases.assign_tradesman.application.request;

import kernel.ApplicationEvent;
import use_cases.assign_tradesman.domain.AssignRequestID;

public class CreateAssignRequestApplicationEvent implements ApplicationEvent {
    private final AssignRequestID assignRequestId;

    public CreateAssignRequestApplicationEvent(AssignRequestID assignRequestId) {
        this.assignRequestId = assignRequestId;
    }
}
