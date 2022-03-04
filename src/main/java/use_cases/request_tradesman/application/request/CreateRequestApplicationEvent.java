package use_cases.request_tradesman.application.request;

import kernel.ApplicationEvent;
import use_cases.request_tradesman.domain.RequestId;

public class CreateRequestApplicationEvent implements ApplicationEvent {
    private final RequestId requestId;

    public CreateRequestApplicationEvent(RequestId requestId) {
        this.requestId = requestId;
    }
}
