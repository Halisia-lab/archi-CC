package use_cases.request_tradesman.application.request_verification;

import kernel.Command;

import use_cases.request_tradesman.application.request.CreateRequest;
import use_cases.request_tradesman.domain.RequestId;

public class VerifyRequestApplication implements Command {
    public final RequestId requestId;
    public final CreateRequest createRequest;

    public VerifyRequestApplication(RequestId requestId, CreateRequest createRequest) {
        this.requestId = requestId;
        this.createRequest = createRequest;
    }
}
