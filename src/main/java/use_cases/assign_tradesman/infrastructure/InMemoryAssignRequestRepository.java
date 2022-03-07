package use_cases.assign_tradesman.infrastructure;

import kernel.DomainEvent;
import use_cases.assign_tradesman.domain.AssignRequest;
import use_cases.assign_tradesman.domain.AssignRequestEventSourcedRepository;
import use_cases.assign_tradesman.domain.AssignRequestID;
import use_cases.assign_tradesman.exception.NoSuchAssignRequestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryAssignRequestRepository implements AssignRequestEventSourcedRepository {
    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<AssignRequestID, List<DomainEvent>> data = new ConcurrentHashMap<>();

    @Override
    public AssignRequestID nextIdentity() {
        return new AssignRequestID(count.incrementAndGet());
    }

    @Override
    public AssignRequest findById(AssignRequestID id) {
        final List<DomainEvent> recordedEvents = id != null ? data.get(id) : null;
        try {

            if (recordedEvents == null) {
                throw NoSuchAssignRequestException.withId(id);
            }
            return AssignRequest.of(id, recordedEvents);
        } catch (Exception e) {
            System.out.println("ERROR: ASSIGN REQUEST DOES NOT EXIST");

        }
        return null;

    }

    @Override
    public void add(AssignRequest assignRequest) {
        final AssignRequestID assignRequestId = assignRequest.id();
        var domainEvents = data.get(assignRequestId);
        if (domainEvents == null) {
            domainEvents = new ArrayList<>();
        }
        domainEvents.addAll(assignRequest.recordedEvents());
        data.put(assignRequest.id(), domainEvents);
    }

    @Override
    public void delete(AssignRequestID id) {
        data.remove(id);
    }

    @Override
    public List<AssignRequest> findAll() {
        List<AssignRequest> result = new ArrayList<>();
        final Set<Map.Entry<AssignRequestID, List<DomainEvent>>> entries = data.entrySet();
        for (Map.Entry<AssignRequestID, List<DomainEvent>> entry : entries) {
            result.add(AssignRequest.of(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}
