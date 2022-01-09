package use_cases.member.application.member_verification;

public class MemberFieldsRestrictions {
    public static final String EMAIL_PATTERN = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    public static final int MIN_PASSWORD_LENGTH = 6;
}
