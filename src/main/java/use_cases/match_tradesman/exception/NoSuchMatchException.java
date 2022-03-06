package use_cases.match_tradesman.exception;



public class NoSuchMatchException extends RuntimeException {
    private NoSuchMatchException(String message) {
        super(message);
    }

}
