package use_cases.member.application.payment_verification;

import kernel.ApplicationEvent;
import use_cases.member.domain.PaymentId;

public class VerifyPaymentApplicationEvent implements ApplicationEvent {
    private final PaymentId paymentId;

    public VerifyPaymentApplicationEvent(PaymentId paymentId) {
        this.paymentId = paymentId;
    }
}
