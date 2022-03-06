package use_cases.assign_tradesman.application.request_verification;


import kernel.Verification;
import use_cases.assign_tradesman.domain.AssignRequestEventSourcedRepository;
import use_cases.assign_tradesman.domain.AssignRequestID;
import use_cases.assign_tradesman.exception.NotValidAssignRequestMatchException;

public class VerifyAssignRequest implements Verification<AssignRequestID> {

    AssignRequestEventSourcedRepository assignRequestEventSourcedRepository;

    public VerifyAssignRequest(AssignRequestEventSourcedRepository assignRequestEventSourcedRepository) {
        this.assignRequestEventSourcedRepository = assignRequestEventSourcedRepository;
    }


    @Override
    public void validate(AssignRequestID assignRequestID) {
        if( assignRequestID == null || assignRequestEventSourcedRepository.findById(assignRequestID) == null ) {
            throw NotValidAssignRequestMatchException.withId(assignRequestID);
        }
    }


}
