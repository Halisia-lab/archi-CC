package kernel;

import domain.MemberId;

public class NotValidTradesmanException extends RuntimeException {
    private NotValidTradesmanException(String message) {
        super(message);
    }

    public static NotValidTradesmanException withId(MemberId id) {
        return new NotValidTradesmanException("Not valid tradesman with ID : " + id);
    }
}
