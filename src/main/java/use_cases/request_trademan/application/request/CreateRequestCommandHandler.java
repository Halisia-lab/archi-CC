package use_cases.request_trademan.application.request;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;

import use_cases.request_trademan.application.request_verification.VerifyRequestApplication;
import use_cases.request_trademan.application.request_verification.VerifyRequestApplicationHandler;
import use_cases.request_trademan.domain.Request;
import use_cases.request_trademan.domain.RequestEventSourcedRepository;
import use_cases.request_trademan.domain.RequestId;

public class CreateRequestCommandHandler implements CommandHandler<CreateRequest, RequestId> {
    private final RequestEventSourcedRepository requestEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final VerifyRequestApplicationHandler verifyRequestApplicationHandler;

    public CreateRequestCommandHandler(RequestEventSourcedRepository requestEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher, VerifyRequestApplicationHandler verifyRequestApplicationHandler) {
        this.requestEventSourcedRepository = requestEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.verifyRequestApplicationHandler = verifyRequestApplicationHandler;
    }

    @Override
    public RequestId handle(CreateRequest createRequest) {
        final RequestId requestId = requestEventSourcedRepository.nextIdentity();
        Request request = Request.of(requestId, createRequest.project, createRequest.creationDate, createRequest.getCandidates(), createRequest.available, createRequest.contractorId);

        try {
            verifyRequestApplicationHandler.handle(new VerifyRequestApplication(requestId,createRequest));
            requestEventSourcedRepository.add(request);
            eventEventDispatcher.dispatch(new CreateRequestApplicationEvent(requestId));
            return requestId;
        } catch(Exception e) {
            System.out.println("ERROR DURING REQUEST TRADMAN: " + e.getMessage());
        }
        return null;
    }
}