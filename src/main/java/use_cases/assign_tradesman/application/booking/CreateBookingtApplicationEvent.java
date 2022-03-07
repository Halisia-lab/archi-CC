package use_cases.assign_tradesman.application.booking;

import kernel.ApplicationEvent;
import use_cases.assign_tradesman.domain.BookingId;

public class CreateBookingtApplicationEvent implements ApplicationEvent {
    private final BookingId bookingId;

    public CreateBookingtApplicationEvent(BookingId bookingId) {
        this.bookingId = bookingId;
    }
}
