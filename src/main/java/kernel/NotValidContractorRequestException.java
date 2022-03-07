package kernel;

import domain.MemberId;

public class NotValidContractorRequestException extends RuntimeException {
    private NotValidContractorRequestException(String message) {
        super(message);
    }

    public static NotValidContractorRequestException withId(MemberId id) {
        return new NotValidContractorRequestException("Not valid contractor with ID : " + id);
    }
}
