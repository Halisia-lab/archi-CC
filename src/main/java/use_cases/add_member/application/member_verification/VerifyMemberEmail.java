package use_cases.add_member.application.member_verification;

import kernel.Verification;
import domain.MemberEventSourcedRepository;
import use_cases.add_member.exception.NotValidApplicationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyMemberEmail implements Verification<String> {
    private final Pattern pattern;
    private final MemberEventSourcedRepository memberEventSourcedRepository;

    public VerifyMemberEmail(MemberEventSourcedRepository memberEventSourcedRepository) {
        this.memberEventSourcedRepository = memberEventSourcedRepository;
        this.pattern = Pattern.compile(MemberFieldsRestrictions.EMAIL_PATTERN);
    }

    @Override
    public void validate(String email) {
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) {
            throw NotValidApplicationException.withEmail(email);
        }
        if(memberEventSourcedRepository.findByEmail(email).isPresent()) {
            throw NotValidApplicationException.withExistingEmail(email);
        }
    }
}
