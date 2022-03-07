package use_cases.assign_tradesman.domain;

import domain.MemberId;
import domain.Project;
import kernel.DomainEvent;
import kernel.Entity;
import java.util.ArrayList;
import java.util.List;

public class AssignRequest implements Entity<AssignRequestID> {

    private final AssignRequestID id;
    private final List<DomainEvent> recordedEvents;

    private Project project;

    private MemberId tradesmanId;
    private MemberId contractorId;

    private AssignRequest(AssignRequestID id, List<DomainEvent> recordedEvents) {
        this.id = id;
        this.recordedEvents = recordedEvents;

    }

    public static AssignRequest of(AssignRequestID assignRequestID, Project project, MemberId tradesmanId, MemberId contractorId) {
        final List<DomainEvent> recordedEvents = new ArrayList<>();
        recordedEvents.add(new CreateAssignRequestEvent(assignRequestID, project,tradesmanId, contractorId));
        return new AssignRequest(assignRequestID, recordedEvents);
    }

    public static AssignRequest of(AssignRequestID id, List<DomainEvent> recordedEvents) {
        final AssignRequest assignRequest = new AssignRequest(id, new ArrayList<>());
        assignRequest.replay(recordedEvents);
        return assignRequest;
    }

    public Project getProject() {
        return project;
    }

    @Override
    public AssignRequestID id() {
        return id;
    }

    @Override
    public List<DomainEvent> recordedEvents() {
        return recordedEvents;
    }

    private void replay(List<DomainEvent> recordedEvents) {
        for (DomainEvent recordedEvent : recordedEvents) {
            if (recordedEvent instanceof CreateAssignRequestEvent) {
                applyEvent((CreateAssignRequestEvent) recordedEvent);
            }
        }
    }

    private void applyEvent(CreateAssignRequestEvent createassignRequestEvent) {
        this.project = createassignRequestEvent.getProject();
        this.tradesmanId = createassignRequestEvent.getTradesmanId();
        this.contractorId = createassignRequestEvent.getContractorId();
    }

    public MemberId getTradesmanId() {
        return tradesmanId;
    }

    public MemberId getContractorId() {
        return contractorId;
    }

//    @Override
//    public String toString() {
//        return "AssignRequest{" +
//                "id=" + id.getValue() +
//                ", project=" + project.getName() +
//                ", contractorId=" + contractorId.getValue()  +
//                ", tradesmanId=" + tradesmanId.getValue() +
//                '}';
//    }
}
