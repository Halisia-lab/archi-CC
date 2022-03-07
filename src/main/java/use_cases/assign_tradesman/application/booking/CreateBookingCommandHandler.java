package use_cases.assign_tradesman.application.booking;

import kernel.CommandHandler;
import kernel.Event;
import kernel.EventDispatcher;
import use_cases.assign_tradesman.domain.Booking;
import use_cases.assign_tradesman.domain.BookingEventSourcedRepository;
import use_cases.assign_tradesman.domain.BookingId;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CreateBookingCommandHandler implements CommandHandler<CreateBooking, BookingId> {
    private ScheduledExecutorService executorService;
    private final BookingEventSourcedRepository bookingEventSourcedRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    public CreateBookingCommandHandler(BookingEventSourcedRepository bookingEventSourcedRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.bookingEventSourcedRepository = bookingEventSourcedRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public BookingId handle(CreateBooking createBooking) {
        final BookingId bookingId = bookingEventSourcedRepository.nextIdentity();
        Booking booking = Booking.of(bookingId, createBooking.startDate, createBooking.duration, createBooking.project, createBooking.contractorID, createBooking.tradesmanId);

        try {
            bookingEventSourcedRepository.add(booking);

            eventEventDispatcher.dispatch(new CreateBookingtApplicationEvent(bookingId));
            return bookingId;
        } catch (Exception e) {
            System.out.println("ERROR DURING Booking PROCESS: " + e.getMessage());
        }
        return null;
    }
}
