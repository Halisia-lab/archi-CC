package use_cases.request_tradesman.exception;

public class NotValidProjectRequestException extends RuntimeException {
    private NotValidProjectRequestException(String message) {
        super(message);
    }

    public static NotValidProjectRequestException withName(String name) {
        return new NotValidProjectRequestException("Not valid project named " + name);
    }
}
