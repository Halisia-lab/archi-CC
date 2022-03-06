package use_cases.add_member.application.payment_verification;

import kernel.Verification;

import use_cases.add_member.exception.NotValidCreditCardException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyPaymentCCNumber implements Verification<String> {
    private final Pattern pattern;
    public VerifyPaymentCCNumber() {
        this.pattern = Pattern.compile(PaymentFieldsRestrictions.CREDITCARD_PATTERN);
    }

    @Override
    public void validate(String number) {
        Matcher matcher = pattern.matcher(number);
        if(!matcher.matches()) {
            throw NotValidCreditCardException.withNumber(number);
        }
    }
}
