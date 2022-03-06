package use_cases.assign_tradesman.exception;

import use_cases.assign_tradesman.domain.AssignRequestID;

public class NotValidAssignRequestMatchException extends RuntimeException {
    private NotValidAssignRequestMatchException(String message) {
        super(message);
    }

    public static NotValidAssignRequestMatchException withId(AssignRequestID assignRequestID) {
        return new NotValidAssignRequestMatchException("Not valid assignRequest with assignRequest ID: " + assignRequestID);
    }
}
