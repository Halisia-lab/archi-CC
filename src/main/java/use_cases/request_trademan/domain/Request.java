package use_cases.request_trademan.domain;

import kernel.DomainEvent;
import kernel.Entity;
import use_cases.add_member.domain.MemberId;
import use_cases.request_trademan.application.member.CreateTradesman;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Request implements Entity<RequestId> {

    private final RequestId id;
    private final List<DomainEvent> recordedEvents;

    private Project project;
    private LocalDate creationDate;
    private boolean available;
    private List<CreateTradesman> candidates;
    private MemberId contractorId;

    private Request(RequestId id, List<DomainEvent> recordedEvents) {
        this.id = id;
        this.recordedEvents = recordedEvents;

    }

    public static Request of(RequestId requestId, Project project, LocalDate creationDate, List<CreateTradesman> candidates, boolean available, MemberId contractorOwner) {
        final List<DomainEvent> recordedEvents = new ArrayList<>();
        recordedEvents.add(new CreateRequestEvent(requestId, project, creationDate,  available, candidates,contractorOwner));
        return new Request(requestId, recordedEvents);
    }

    public static Request of(RequestId id, List<DomainEvent> recordedEvents) {
        final Request request = new Request(id, new ArrayList<>());
        request.replay(recordedEvents);
        return request;
    }


    @Override
    public RequestId id() {
        return id;
    }

    @Override
    public List<DomainEvent> recordedEvents() {
        return recordedEvents;
    }

    private void replay(List<DomainEvent> recordedEvents) {
        for (DomainEvent recordedEvent : recordedEvents) {
            if (recordedEvent instanceof CreateRequestEvent) {
                applyEvent((CreateRequestEvent) recordedEvent);
            }
        }
    }

    private void applyEvent(CreateRequestEvent createRequestEvent) {
        this.project = createRequestEvent.getProject();
        this.creationDate = createRequestEvent.getCreationDate();
        this.available = createRequestEvent.isAvailable();
        this.candidates = createRequestEvent.getCandidates();
        this.contractorId = createRequestEvent.getContractorId();
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id.getValue() +
                ", project='" + project.getName() + '\'' +
                ", date=" + creationDate + '\'' +
                ", available=" + available + '\'' +
                '}';
    }
}
