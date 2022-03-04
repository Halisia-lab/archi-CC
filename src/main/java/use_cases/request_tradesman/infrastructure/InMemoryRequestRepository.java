package use_cases.request_tradesman.infrastructure;

import kernel.DomainEvent;

import use_cases.request_tradesman.domain.Request;
import use_cases.request_tradesman.domain.RequestEventSourcedRepository;
import use_cases.request_tradesman.domain.RequestId;
import use_cases.request_tradesman.exception.NoSuchRequestException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryRequestRepository implements RequestEventSourcedRepository {
    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<RequestId, List<DomainEvent>> data = new ConcurrentHashMap<>();

    @Override
    public RequestId nextIdentity() {
        return new RequestId(count.incrementAndGet());
    }

    @Override
    public Request findById(RequestId id) {
        final List<DomainEvent> recordedEvents = id != null ? data.get(id) : null;
        try {

            if (recordedEvents == null) {
                throw NoSuchRequestException.withId(id);
            }
            return Request.of(id, recordedEvents);
        } catch (Exception e) {
            System.out.println("ERROR: REQUEST DOES NOT EXIST");

        }
        return null;

    }

    @Override
    public void add(Request request) {
        final RequestId requestId = request.id();
        var domainEvents = data.get(requestId);
        if (domainEvents == null) {
            domainEvents = new ArrayList<>();
        }
        domainEvents.addAll(request.recordedEvents());
        data.put(request.id(), domainEvents);
    }

    @Override
    public void delete(RequestId id) {
        data.remove(id);
    }

    @Override
    public List<Request> findAll() {
        List<Request> result = new ArrayList<>();
        final Set<Map.Entry<RequestId, List<DomainEvent>>> entries = data.entrySet();
        for (Map.Entry<RequestId, List<DomainEvent>> entry : entries) {
            result.add(Request.of(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}
