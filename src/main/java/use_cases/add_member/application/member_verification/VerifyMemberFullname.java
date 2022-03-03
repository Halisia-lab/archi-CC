package use_cases.add_member.application.member_verification;

import kernel.Verification;
import use_cases.add_member.application.member.CreateMember;
import use_cases.add_member.exception.NotValidApplicationException;

public class VerifyMemberFullname implements Verification<CreateMember> {

    public VerifyMemberFullname() {
    }

    @Override
    public void validate(CreateMember createMember) {
        if(createMember.firstname.equals("") || createMember.lastname.equals("")) {
            throw NotValidApplicationException.emptyName();
        }
    }
}
