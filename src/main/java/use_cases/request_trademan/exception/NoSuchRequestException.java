package use_cases.request_trademan.exception;

import use_cases.add_member.domain.MemberId;
import use_cases.add_member.exception.NoSuchMemberException;
import use_cases.request_trademan.domain.RequestId;

public class NoSuchRequestException extends RuntimeException {
    private NoSuchRequestException(String message) {
        super(message);
    }

    public static NoSuchRequestException withId(RequestId id) {
        return new NoSuchRequestException("No request found with ID " + id.getValue());
    }
}
