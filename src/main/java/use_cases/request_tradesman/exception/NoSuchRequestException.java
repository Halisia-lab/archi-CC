package use_cases.request_tradesman.exception;

import use_cases.request_tradesman.domain.RequestId;

public class NoSuchRequestException extends RuntimeException {
    private NoSuchRequestException(String message) {
        super(message);
    }

    public static NoSuchRequestException withId(RequestId id) {
        return new NoSuchRequestException("No request found with ID " + id);
    }
}
