package use_cases.request_trademan.application.request_verification;

import kernel.ApplicationEvent;
import use_cases.add_member.domain.MemberId;
import use_cases.request_trademan.domain.RequestId;

public class VerifyRequestApplicationEvent implements ApplicationEvent {
    private final RequestId requestId;

    public VerifyRequestApplicationEvent(RequestId requestId) {
        this.requestId = requestId;
    }
}
