package use_cases.request_tradesman.application.request_verification;

import kernel.ApplicationEvent;
import use_cases.request_tradesman.domain.RequestId;

public class VerifyRequestApplicationEvent implements ApplicationEvent {
    private final RequestId requestId;

    public VerifyRequestApplicationEvent(RequestId requestId) {
        this.requestId = requestId;
    }
}
