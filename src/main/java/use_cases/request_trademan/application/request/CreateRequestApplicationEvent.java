package use_cases.request_trademan.application.request;

import kernel.ApplicationEvent;
import use_cases.request_trademan.domain.RequestId;

public class CreateRequestApplicationEvent implements ApplicationEvent {
    private final RequestId requestId;

    public CreateRequestApplicationEvent(RequestId requestId) {
        this.requestId = requestId;
    }
}
