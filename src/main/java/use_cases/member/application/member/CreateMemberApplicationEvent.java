package use_cases.member.application.member;

import kernel.ApplicationEvent;
import use_cases.member.domain.MemberId;

public class CreateMemberApplicationEvent implements ApplicationEvent {
    private final MemberId memberId;

    public CreateMemberApplicationEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
