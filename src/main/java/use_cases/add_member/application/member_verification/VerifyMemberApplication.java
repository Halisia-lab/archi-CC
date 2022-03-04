package use_cases.add_member.application.member_verification;

import kernel.Command;
import use_cases.add_member.application.member.CreateMember;
import domain.MemberId;

public class VerifyMemberApplication implements Command {
    public final MemberId memberId;
    public final CreateMember createMember;

    public VerifyMemberApplication(MemberId memberId, CreateMember createMember) {
        this.memberId = memberId;
        this.createMember = createMember;
    }
}
