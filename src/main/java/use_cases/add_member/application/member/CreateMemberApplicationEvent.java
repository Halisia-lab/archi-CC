package use_cases.add_member.application.member;

import kernel.ApplicationEvent;
import domain.MemberId;

public class CreateMemberApplicationEvent implements ApplicationEvent {
    private final MemberId memberId;

    public CreateMemberApplicationEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
