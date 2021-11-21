package domain.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotValidCreditCardException extends RuntimeException {
    private NotValidCreditCardException(String message) {
        super(message);
    }

    public static NotValidCreditCardException noCreditCard() {
        return new NotValidCreditCardException("No credit card has been configured");
    }

    public static NotValidCreditCardException withNumber(String number) {
        return new NotValidCreditCardException("Incorrect credit card number : " + number);
    }

    public static NotValidCreditCardException withDate(LocalDate expirationDate) {
        return new NotValidCreditCardException("The credit card has expired on " + expirationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public static NotValidCreditCardException withName(String holderName) {
        return new NotValidCreditCardException("The holder name "+ holderName + " and the member don't match");
    }
}
