package use_cases.add_member.exception;

import use_cases.add_member.application.payment_verification.PaymentFieldsRestrictions;

public final class NotValidPaymentException extends RuntimeException {
    private NotValidPaymentException(String message) {
        super(message);
    }

    public static NotValidPaymentException withAmount(Number amount) {
        return new NotValidPaymentException("Payment amount is " + amount + " instead of " + PaymentFieldsRestrictions.APPLICATION_AMOUNT);
    }
}
