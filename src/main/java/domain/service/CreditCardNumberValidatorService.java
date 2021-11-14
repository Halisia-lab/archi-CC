package domain.service;

import domain.model.CreditCard;
import domain.model.Member;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardNumberValidatorService {
    private static final String CREDITCARD_PATTERN = "^[0-9]{12}$";

    private final Pattern pattern;

    public CreditCardNumberValidatorService() {
        this.pattern = Pattern.compile(CREDITCARD_PATTERN);
    }

    public boolean validateThatOf(Member member) {
        Matcher matcher = pattern.matcher(member.getCreditCard().getNumber());
        return matcher.matches();
    }
}
