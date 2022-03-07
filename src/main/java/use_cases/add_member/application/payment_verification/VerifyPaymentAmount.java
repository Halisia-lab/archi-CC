package use_cases.add_member.application.payment_verification;

import kernel.Verification;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.exception.NotValidPaymentException;

public class VerifyPaymentAmount implements Verification<CreatePayment> {

    public VerifyPaymentAmount() {
    }

    @Override
    public void validate(CreatePayment createPayment) {
        if(!createPayment.amount.equals(PaymentFieldsRestrictions.APPLICATION_AMOUNT)) {
            throw NotValidPaymentException.withAmount(createPayment.amount);
        }
    }
}
