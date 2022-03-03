package use_cases.add_member.exception;

import use_cases.add_member.domain.PaymentId;

public class NoSuchPaymentException extends RuntimeException {
    private NoSuchPaymentException(String message) {
        super(message);
    }

    public static NoSuchPaymentException withId(PaymentId id) {
        return new NoSuchPaymentException("No payment found with ID " + id.getValue());
    }
}
