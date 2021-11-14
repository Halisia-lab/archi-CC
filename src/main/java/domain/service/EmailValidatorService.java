package domain.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorService {
    private final Pattern pattern;

    private static final String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    public EmailValidatorService() {
        this.pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
