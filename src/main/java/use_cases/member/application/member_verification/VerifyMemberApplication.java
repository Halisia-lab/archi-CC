package use_cases.member.application.member_verification;

import kernel.Command;
import use_cases.member.application.member.CreateMember;
import use_cases.member.domain.MemberId;

public class VerifyMemberApplication implements Command {
    public final MemberId memberId;
    public final CreateMember createMember;

    public VerifyMemberApplication(MemberId memberId, CreateMember createMember) {
        this.memberId = memberId;
        this.createMember = createMember;
    }
}
