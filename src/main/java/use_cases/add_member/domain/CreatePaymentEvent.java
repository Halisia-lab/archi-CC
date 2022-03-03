package use_cases.add_member.domain;

import kernel.DomainEvent;
import use_cases.add_member.application.member.CreateMember;

import java.time.LocalDateTime;

public class CreatePaymentEvent implements DomainEvent {
    private final PaymentId id;
    private final Number amount;
    private final CreateMember owner;
    private final LocalDateTime createDate;

    public CreatePaymentEvent(PaymentId id, Number amount, CreateMember owner, LocalDateTime createDate) {
        this.id = id;
        this.amount = amount;
        this.owner = owner;
        this.createDate = createDate;
    }

    public PaymentId getId() {
        return id;
    }

    public Number getAmount() {
        return amount;
    }

    public CreateMember getOwner() {
        return owner;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
