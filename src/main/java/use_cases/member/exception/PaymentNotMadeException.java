package use_cases.member.exception;

public class PaymentNotMadeException extends RuntimeException{
    private PaymentNotMadeException(String message) {
        super(message);
    }

    public static PaymentNotMadeException withAccount(String email) {
        return new PaymentNotMadeException("No payment for this account: " + email);
    }

}
