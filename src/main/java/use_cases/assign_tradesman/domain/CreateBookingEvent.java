package use_cases.assign_tradesman.domain;

import domain.MemberId;
import domain.Project;
import kernel.DomainEvent;

import java.time.Duration;
import java.time.LocalDate;

public class CreateBookingEvent implements DomainEvent {

    private BookingId bookingId;
    private LocalDate startDate;
    private Duration duration;
    private Project project;
    private MemberId contractorID;
    private MemberId tradesmanId;

    public CreateBookingEvent(BookingId bookingId, LocalDate startDate, Duration duration, Project project, MemberId contractorID, MemberId tradesmanId) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.duration = duration;
        this.project = project;
        this.contractorID = contractorID;
        this.tradesmanId = tradesmanId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public Project getProject() {
        return project;
    }

    public MemberId getContractorID() {
        return contractorID;
    }

    public MemberId getTradesmanId() {
        return tradesmanId;
    }
}
