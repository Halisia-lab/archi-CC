package use_cases.request_trademan.exception;

import use_cases.add_member.domain.MemberId;

public class NotValidContractorRequestException extends RuntimeException {
    private NotValidContractorRequestException(String message) {
        super(message);
    }

    public static NotValidContractorRequestException withId(MemberId id) {
        return new NotValidContractorRequestException("Not valid contractor with ID : " + id);
    }
}
