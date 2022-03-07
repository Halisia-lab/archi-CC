package use_cases.match_tradesman.exception;


import domain.Tradesman;

public class NotValidTradesmanMatchException extends RuntimeException {
    private NotValidTradesmanMatchException() {
        super("Matching tradesman is null ");
    }

    public static NotValidTradesmanMatchException withTradesman(Tradesman tradesman) {
        return new NotValidTradesmanMatchException();
    }
}
