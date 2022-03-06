package use_cases.assign_tradesman.application.booking;

import domain.MemberId;
import domain.Project;
import kernel.Command;

import java.time.Duration;
import java.time.LocalDate;

public class CreateBooking implements Command {

    public final LocalDate startDate;
    public final Duration duration;
    public final Project project;
    public final MemberId contractorID;
    public final MemberId tradesmanId;


    public CreateBooking(LocalDate startDate, Duration duration, Project project, MemberId contractorID, MemberId tradesmanId) {
        this.startDate = startDate;
        this.duration = duration;
        this.project = project;
        this.contractorID = contractorID;
        this.tradesmanId = tradesmanId;
    }
}

