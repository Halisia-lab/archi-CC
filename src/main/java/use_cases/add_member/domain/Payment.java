package use_cases.add_member.domain;

import kernel.DomainEvent;
import kernel.Entity;
import use_cases.add_member.application.member.CreateMember;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Payment implements Entity<PaymentId> {
    private final PaymentId id;
    private final List<DomainEvent> recordedEvents;

    private Number amount;
    private CreateMember owner;
    private LocalDateTime createDate;

    public Payment(PaymentId id, List<DomainEvent> recordedEvents) {
        this.id = id;
        this.recordedEvents = recordedEvents;
    }

    public static Payment of(PaymentId paymentId, Number amount, CreateMember owner, LocalDateTime dateTime) {
        final List<DomainEvent> recordedEvents = new ArrayList<>();
        recordedEvents.add(new CreatePaymentEvent(paymentId, amount, owner, dateTime));
        return new Payment(paymentId, recordedEvents);
    }

    public static Payment of(PaymentId id, List<DomainEvent> recordedEvents) {
        final Payment payment = new Payment(id, new ArrayList<>());
        payment.replay(recordedEvents);
        return payment;
    }

    @Override
    public PaymentId id() {
        return id;
    }

    @Override
    public List<DomainEvent> recordedEvents() {
        return recordedEvents;
    }

    private void replay(List<DomainEvent> recordedEvents) {
        for (DomainEvent recordedEvent : recordedEvents) {
            if (recordedEvent instanceof CreatePaymentEvent) {
                applyEvent((CreatePaymentEvent) recordedEvent);
            }
        }
    }

    private void applyEvent(CreatePaymentEvent createPaymentEvent) {
        this.amount = createPaymentEvent.getAmount();
        this.owner = createPaymentEvent.getOwner();
        this.createDate = createPaymentEvent.getCreateDate();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id.getValue() +
                ", amount='" + amount + "€" + '\'' +
                ", owner='" + owner.firstname + " " + owner.lastname +  '\'' +
                ", dateTime=" + createDate + '\'' +
                '}';
    }
}
