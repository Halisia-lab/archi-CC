package use_cases.assign_tradesman.domain;

import domain.MemberId;
import domain.Project;
import kernel.DomainEvent;
import kernel.Entity;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Booking implements Entity<BookingId> {

    private BookingId bookingId;
    private LocalDate startDate;
    private Duration duration;
    private Project project;
    private MemberId contractorID;
    private MemberId tradesmanId;
    private final List<DomainEvent> recordedEvents;


    public Booking(BookingId bookingId, List<DomainEvent> recordedEvents) {
        this.bookingId = bookingId;
        this.recordedEvents = recordedEvents;
    }

    public static Booking of(BookingId bookingId, LocalDate startDate, Duration duration, Project project,
                             MemberId contractorID, MemberId tradesmanId) {
        final List<DomainEvent> recordedEvents = new ArrayList<>();
        recordedEvents.add(new CreateBookingEvent(bookingId, startDate, duration, project, contractorID, tradesmanId));
        return new Booking(bookingId, recordedEvents);
    }

    public static Booking of(BookingId id, List<DomainEvent> recordedEvents) {
        final Booking booking = new Booking(id, new ArrayList<>());
        booking.replay(recordedEvents);
        return booking;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public MemberId getTradesmanId() {
        return tradesmanId;
    }

    @Override
    public BookingId id() {
        return bookingId;
    }

    @Override
    public List<DomainEvent> recordedEvents() {
        return recordedEvents;
    }

    protected void replay(List<DomainEvent> recordedEvents) {
        for (DomainEvent recordedEvent : recordedEvents) {
            if (recordedEvent instanceof CreateBookingEvent) {
                applyEvent((CreateBookingEvent) recordedEvent);
            }
        }
    }

    private void applyEvent(CreateBookingEvent createBookingEvent) {

        this.startDate = createBookingEvent.getStartDate();
        this.duration = createBookingEvent.getDuration();
        this.project = createBookingEvent.getProject();
        this.contractorID = createBookingEvent.getContractorID();
        this.tradesmanId = createBookingEvent.getTradesmanId();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId.getValue() +
                ", startDate=" + startDate +
                ", duration=" + duration.toDays() +
                ", project=" + project.getName() +
                ", contractorID=" + contractorID.getValue() +
                ", tradesmanId=" + tradesmanId.getValue() +
                '}';
    }
}
