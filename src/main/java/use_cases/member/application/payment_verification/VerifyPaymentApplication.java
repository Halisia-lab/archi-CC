package use_cases.member.application.payment_verification;

import kernel.Command;
import use_cases.member.application.payment.CreatePayment;
import use_cases.member.domain.PaymentId;

public class VerifyPaymentApplication implements Command {
    public final PaymentId paymentId;
    public final CreatePayment createPayment;

    public VerifyPaymentApplication(PaymentId paymentId, CreatePayment createPayment) {
        this.paymentId = paymentId;
        this.createPayment = createPayment;
    }
}
