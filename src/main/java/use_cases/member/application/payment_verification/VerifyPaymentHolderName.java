package use_cases.member.application.payment_verification;

import kernel.Verification;
import use_cases.member.application.payment.CreatePayment;
import use_cases.member.exception.NotValidCreditCardException;

public class VerifyPaymentHolderName implements Verification<CreatePayment> {
    public VerifyPaymentHolderName() {
    }

    @Override
    public void validate(CreatePayment createPayment) {
        if(!createPayment.creditCard.getHolderName().equalsIgnoreCase(createPayment.owner.lastname + " " + createPayment.owner.firstname)) {
            throw NotValidCreditCardException.withName(createPayment.creditCard.getHolderName());
        }
    }
}
