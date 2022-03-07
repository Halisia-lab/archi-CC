package use_cases.assign_tradesman.exception;

import domain.MemberId;
import use_cases.assign_tradesman.domain.AssignRequestID;

public class UnavailableTradesmanException extends RuntimeException {
    private UnavailableTradesmanException(String message) {
        super(message);
    }

    public static UnavailableTradesmanException withId(MemberId tradesmanId) {
        return new UnavailableTradesmanException("Tradesman with  id : " + tradesmanId + " is not available");
    }
}
