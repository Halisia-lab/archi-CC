package use_cases.assign_tradesman.domain;

import domain.MemberId;
import kernel.EventSourcedRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingEventSourcedRepository extends EventSourcedRepository<BookingId, Booking> {
    List<Booking> findAll();
    List<Booking> findAllByTradesmanId(MemberId tradesmanId);
    List<Booking> findAllByTradesmanEndingAfterDate(MemberId tradesmanId, LocalDate startDate);
}
