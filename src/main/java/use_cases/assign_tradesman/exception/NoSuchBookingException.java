package use_cases.assign_tradesman.exception;

import use_cases.assign_tradesman.domain.BookingId;

public class NoSuchBookingException extends RuntimeException {
    private NoSuchBookingException(String message) {
        super(message);
    }

    public static NoSuchBookingException withId(BookingId id) {
        return new NoSuchBookingException("No booking found with ID " + id.getValue());
    }
}
