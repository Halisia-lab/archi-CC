package use_cases.match_tradesman.exception;


import domain.Tradesman;

public class NotValidTradesmanMatchException extends RuntimeException {
    private NotValidTradesmanMatchException(String message) {
        super(message);
    }

    public static NotValidTradesmanMatchException withTradesman(Tradesman tradesman) {
        return new NotValidTradesmanMatchException("Matching tradesman is null ");
    }
}
