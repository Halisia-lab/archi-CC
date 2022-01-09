package use_cases.member.application.payment;

import kernel.ApplicationEvent;
import use_cases.member.domain.PaymentId;

public class CreatePaymentApplicationEvent implements ApplicationEvent {
    private final PaymentId paymentId;

    public CreatePaymentApplicationEvent(PaymentId paymentId) {
        this.paymentId = paymentId;
    }
}
