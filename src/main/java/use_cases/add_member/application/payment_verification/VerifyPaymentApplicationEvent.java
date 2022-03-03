package use_cases.add_member.application.payment_verification;

import kernel.ApplicationEvent;
import use_cases.add_member.domain.PaymentId;

public class VerifyPaymentApplicationEvent implements ApplicationEvent {
    private final PaymentId paymentId;

    public VerifyPaymentApplicationEvent(PaymentId paymentId) {
        this.paymentId = paymentId;
    }
}
