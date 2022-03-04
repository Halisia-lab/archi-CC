package use_cases.add_member.application.member_verification;

import kernel.ApplicationEvent;
import domain.MemberId;

public class VerifyMemberApplicationEvent implements ApplicationEvent {
    private final MemberId memberId;

    public VerifyMemberApplicationEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
