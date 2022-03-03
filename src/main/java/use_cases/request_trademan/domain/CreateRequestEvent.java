package use_cases.request_trademan.domain;

import kernel.DomainEvent;
import use_cases.add_member.domain.MemberId;
import use_cases.request_trademan.application.member.CreateTradesman;

import java.time.LocalDate;
import java.util.List;

public class CreateRequestEvent implements DomainEvent {
    private RequestId id;
    private Project project;
    private LocalDate creationDate;
    private boolean available;
    private List<CreateTradesman> candidates;
    private MemberId contractorId;

    public CreateRequestEvent(RequestId requestId, Project project, LocalDate creationDate, boolean available, List<CreateTradesman> candidates, MemberId contractorId) {
        this.id = requestId;
        this.project = project;
        this.creationDate = creationDate;
        this.available = available;
        this.candidates = candidates;
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

    public List<CreateTradesman> getCandidates() {
        return candidates;
    }

    public MemberId getContractorId() {
        return contractorId;
    }
}
