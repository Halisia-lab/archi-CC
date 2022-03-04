package use_cases.match_tradesman.exception;

//import use_cases.match_tradesman.domain.MatchId;
import use_cases.request_tradesman.domain.RequestId;

public class NoSuchMatchException extends RuntimeException {
    private NoSuchMatchException(String message) {
        super(message);
    }

    /*public static NoSuchMatchException withId(MatchId id) {
        //return new NoSuchMatchException("No match found with ID " + id.getValue());
    }*/
}
