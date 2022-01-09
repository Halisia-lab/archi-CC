package use_cases.member.exception;

import use_cases.member.domain.PaymentId;

public class NoSuchPaymentException extends RuntimeException {
    private NoSuchPaymentException(String message) {
        super(message);
    }

    public static NoSuchPaymentException withId(PaymentId id) {
        return new NoSuchPaymentException("No payment found with ID " + id.getValue());
    }
}
