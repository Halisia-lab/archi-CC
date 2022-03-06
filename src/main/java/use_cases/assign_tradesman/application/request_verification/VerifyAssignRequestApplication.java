package use_cases.assign_tradesman.application.request_verification;

import kernel.Command;
import use_cases.assign_tradesman.application.request.CreateAssignRequest;
import use_cases.assign_tradesman.domain.AssignRequestID;

public class VerifyAssignRequestApplication implements Command {
    public final AssignRequestID assignRequestID;
    public final CreateAssignRequest createAssignRequest;

    public VerifyAssignRequestApplication(AssignRequestID assignRequestID, CreateAssignRequest createAssignRequest) {
        this.assignRequestID = assignRequestID;
        this.createAssignRequest = createAssignRequest;
    }
}
