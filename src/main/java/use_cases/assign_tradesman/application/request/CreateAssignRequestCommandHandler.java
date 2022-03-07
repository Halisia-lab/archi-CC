package use_cases.assign_tradesman.application.request;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.assign_tradesman.application.request_verification.VerifyAssignRequestApplication;
import use_cases.assign_tradesman.application.request_verification.VerifyAssignRequestApplicationHandler;
import use_cases.assign_tradesman.domain.AssignRequest;
import use_cases.assign_tradesman.domain.AssignRequestEventSourcedRepository;
import use_cases.assign_tradesman.domain.AssignRequestID;


public class CreateAssignRequestCommandHandler implements CommandHandler<CreateAssignRequest, AssignRequestID> {
    private final AssignRequestEventSourcedRepository assignRequestEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final VerifyAssignRequestApplicationHandler verifyAssignRequestApplicationHandler;

    public CreateAssignRequestCommandHandler(AssignRequestEventSourcedRepository assignRequestEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher, VerifyAssignRequestApplicationHandler verifyAssignRequestApplicationHandler) {
        this.assignRequestEventSourcedRepository = assignRequestEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.verifyAssignRequestApplicationHandler = verifyAssignRequestApplicationHandler;
    }

    @Override
    public AssignRequestID handle(CreateAssignRequest createAssignRequest) {
        try {
        final AssignRequestID assignRequestId = assignRequestEventSourcedRepository.nextIdentity();
        AssignRequest assignRequest = AssignRequest.of(assignRequestId, createAssignRequest.project, createAssignRequest.tradesmanId, createAssignRequest.contractorId);


            verifyAssignRequestApplicationHandler.handle(new VerifyAssignRequestApplication(assignRequestId,createAssignRequest));
            assignRequestEventSourcedRepository.add(assignRequest);
            eventEventDispatcher.dispatch(new CreateAssignRequestApplicationEvent(assignRequestId));
            return assignRequestId;
        } catch(Exception e) {
            System.out.println("ERROR DURING ASSIGN REQUEST TRADESMAN: " + e.getMessage());
        }
        return null;
    }
}
