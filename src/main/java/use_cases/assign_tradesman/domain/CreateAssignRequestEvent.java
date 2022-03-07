package use_cases.assign_tradesman.domain;

import domain.MemberId;
import domain.Project;
import kernel.DomainEvent;


public class CreateAssignRequestEvent implements DomainEvent {
    private final AssignRequestID id;
    private final Project project;

    private final MemberId tradesmanId;
    private final MemberId contractorId;

    public CreateAssignRequestEvent(AssignRequestID assignRequestID, Project project, MemberId tradesmanId, MemberId contractorId) {
        this.id = assignRequestID;
        this.project = project;
        this.tradesmanId = tradesmanId;
        this.contractorId = contractorId;
    }

    public Project getProject() {
        return project;
    }

    public MemberId getTradesmanId() {
        return tradesmanId;
    }

    public MemberId getContractorId() {
        return contractorId;
    }
}
