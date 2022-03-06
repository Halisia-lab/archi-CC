package use_cases.assign_tradesman.exception;

import use_cases.assign_tradesman.domain.AssignRequestID;

public class NoSuchAssignRequestException extends RuntimeException {
    private NoSuchAssignRequestException(String message) {
        super(message);
    }

    public static NoSuchAssignRequestException withId(AssignRequestID id) {
        return new NoSuchAssignRequestException("No assign request found with ID " + id);
    }
}
