package use_cases.add_member.domain;

import kernel.EventSourcedRepository;

import java.util.List;

public interface PaymentEventSourcedRepository extends EventSourcedRepository<PaymentId, Payment> {
    List<Payment> findAll();
}
