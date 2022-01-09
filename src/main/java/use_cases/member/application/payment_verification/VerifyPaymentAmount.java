package use_cases.member.application.payment_verification;

import kernel.Verification;
import use_cases.member.application.payment.CreatePayment;
import use_cases.member.exception.NotValidPaymentException;

public class VerifyPaymentAmount implements Verification<CreatePayment> {

    public VerifyPaymentAmount() {
    }

    @Override
    public void validate(CreatePayment createPayment) {
        if(createPayment.amount != PaymentFieldsRestrictions.APPLICATION_AMOUNT) {
            throw NotValidPaymentException.withAmount(createPayment.amount);
        }
    }
}
