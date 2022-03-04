package use_cases.request_tradesman.domain;

import domain.Project;
import kernel.DomainEvent;
import domain.MemberId;

import java.time.LocalDate;

public class CreateRequestEvent implements DomainEvent {
    private final RequestId id;
    private final Project project;
    private final LocalDate creationDate;
    private final boolean available;
    private final MemberId contractorId;

    public CreateRequestEvent(RequestId requestId, Project project, LocalDate creationDate, boolean available, MemberId contractorId) {
        this.id = requestId;
        this.project = project;
        this.creationDate = creationDate;
        this.available = available;
        this.contractorId = contractorId;
    }

    public Project getProject() {
        return project;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public MemberId getContractorId() {
        return contractorId;
    }
}
