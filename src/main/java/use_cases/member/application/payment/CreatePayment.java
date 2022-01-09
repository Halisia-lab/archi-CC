package use_cases.member.application.payment;

import kernel.Command;
import use_cases.member.application.member.CreateMember;
import use_cases.member.domain.CreditCard;

import java.time.LocalDateTime;

public class CreatePayment implements Command {
    public final Number amount;
    public final CreateMember owner;
    public final CreditCard creditCard;
    public final LocalDateTime createDate;

    public CreatePayment(Number amount, CreateMember owner, CreditCard creditCard) {
        this.amount = amount;
        this.owner = owner;
        this.creditCard = creditCard;
        this.createDate = LocalDateTime.now();
    }
}

