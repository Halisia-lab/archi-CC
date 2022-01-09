package use_cases.member.application.member_verification;

import kernel.Verification;
import use_cases.member.exception.NotValidApplicationException;

public class VerifyMemberPassword implements Verification<String> {

    public VerifyMemberPassword() {
    }

    @Override
    public void validate(String password) {
        if(password.length() < MemberFieldsRestrictions.MIN_PASSWORD_LENGTH) {
            throw NotValidApplicationException.withPassword(password);
        }
    }
}
