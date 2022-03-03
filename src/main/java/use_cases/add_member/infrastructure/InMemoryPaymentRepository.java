package use_cases.add_member.infrastructure;

import kernel.DomainEvent;
import use_cases.add_member.domain.*;
import use_cases.add_member.exception.NoSuchPaymentException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPaymentRepository implements PaymentEventSourcedRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<PaymentId, List<DomainEvent>> data = new ConcurrentHashMap<>();


    @Override
    public PaymentId nextIdentity() {
        return new PaymentId(count.incrementAndGet());
    }

    @Override
    public Payment findById(PaymentId id) {
        final List<DomainEvent> recordedEvents = data.get(id);
        if (recordedEvents == null) {
            throw NoSuchPaymentException.withId(id);
        }
        return Payment.of(id, recordedEvents);
    }

    @Override
    public void add(Payment payment) {
        final PaymentId paymentId = payment.id();
        var domainEvents = data.get(paymentId);
        if (domainEvents == null) {
            domainEvents = new ArrayList<>();
        }
        domainEvents.addAll(payment.recordedEvents());
        data.put(payment.id(), domainEvents);
    }

    @Override
    public void delete(PaymentId id) {
        data.remove(id);
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> result = new ArrayList<>();
        final Set<Map.Entry<PaymentId, List<DomainEvent>>> entries = data.entrySet();
        for (Map.Entry<PaymentId, List<DomainEvent>> entry : entries) {
            result.add(Payment.of(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}
