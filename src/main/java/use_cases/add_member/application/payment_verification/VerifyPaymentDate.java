package use_cases.add_member.application.payment_verification;

import kernel.Verification;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.exception.NotValidCreditCardException;

import java.time.LocalDate;

public class VerifyPaymentDate implements Verification<CreatePayment> {

    public VerifyPaymentDate() {
    }

    @Override
    public void validate(CreatePayment createPayment) {
        if(createPayment.creditCard.getExpirationDate().isBefore(LocalDate.now())) {
            throw NotValidCreditCardException.withDate(createPayment.creditCard.getExpirationDate());
        }
    }
}
