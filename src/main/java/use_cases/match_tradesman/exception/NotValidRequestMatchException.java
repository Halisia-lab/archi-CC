package use_cases.match_tradesman.exception;

import use_cases.request_tradesman.domain.RequestId;

public class NotValidRequestMatchException extends RuntimeException {
    private NotValidRequestMatchException(String message) {
        super(message);
    }

    public static NotValidRequestMatchException withId(RequestId requestId) {
        return new NotValidRequestMatchException("Not valid matchTradeManEngin with request ID: " + requestId);
    }
}
