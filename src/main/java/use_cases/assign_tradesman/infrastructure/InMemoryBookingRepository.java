package use_cases.assign_tradesman.infrastructure;

import domain.MemberId;
import kernel.DomainEvent;
import use_cases.assign_tradesman.domain.Booking;
import use_cases.assign_tradesman.domain.BookingEventSourcedRepository;
import use_cases.assign_tradesman.domain.BookingId;
import use_cases.assign_tradesman.exception.NoSuchBookingException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBookingRepository implements BookingEventSourcedRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<BookingId, List<DomainEvent>> data = new ConcurrentHashMap<>();


    @Override
    public BookingId nextIdentity() {
        return new BookingId(count.incrementAndGet());
    }

    @Override
    public Booking findById(BookingId id) {
        final List<DomainEvent> recordedEvents = data.get(id);
        if (recordedEvents == null) {
            throw NoSuchBookingException.withId(id);
        }
        return Booking.of(id, recordedEvents);
    }

    @Override
    public void add(Booking booking) {
        final BookingId bookingId = booking.id();
        var domainEvents = data.get(bookingId);
        if (domainEvents == null) {
            domainEvents = new ArrayList<>();
        }
        domainEvents.addAll(booking.recordedEvents());
        data.put(booking.id(), domainEvents);
    }

    @Override
    public void delete(BookingId id) {
        data.remove(id);
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> result = new ArrayList<>();
        final Set<Map.Entry<BookingId, List<DomainEvent>>> entries = data.entrySet();
        for (Map.Entry<BookingId, List<DomainEvent>> entry : entries) {
            result.add(Booking.of(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    @Override
    public List<Booking> findAllByTradesmanId(MemberId tradesmanId) {
        List<Booking> result = new ArrayList<>();
        final Set<Map.Entry<BookingId, List<DomainEvent>>> entries = data.entrySet();
        for (Map.Entry<BookingId, List<DomainEvent>> entry : entries) {
            if(Booking.of(entry.getKey(), entry.getValue()).getTradesmanId().equals(tradesmanId)) {
                result.add(Booking.of(entry.getKey(), entry.getValue()));
            }
        }
        return result;
    }

    @Override
    public List<Booking> findAllByTradesmanEndingAfterDate(MemberId tradesmanId, LocalDate startDate) {
        List<Booking> result = new ArrayList<>();
        final Set<Map.Entry<BookingId, List<DomainEvent>>> entries = data.entrySet();
        for (Map.Entry<BookingId, List<DomainEvent>> entry : entries) {
            if(Booking.of(entry.getKey(), entry.getValue()).getTradesmanId().equals(tradesmanId)
            && Booking.of(entry.getKey(), entry.getValue()).getStartDate().plusDays(
                    Booking.of(entry.getKey(), entry.getValue()).getDuration().toDays()).isAfter(startDate)) {
                result.add(Booking.of(entry.getKey(), entry.getValue()));
            }
        }
        return result;
    }
}
