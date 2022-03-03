package use_cases.request_trademan.application.request;

import use_cases.request_trademan.application.member.CreateTradesman;
import use_cases.request_trademan.domain.Project;
import use_cases.request_trademan.domain.RequestId;

import java.time.LocalDate;
import java.util.List;

public class RequestDTO {
    private final RequestId id;
    private final Project project;
    private final LocalDate creationDate;
    private final List<CreateTradesman> candidates;
    private final boolean available;

    public RequestDTO(RequestId id, Project project, LocalDate creationDate, List<CreateTradesman> candidates , boolean available) {
        this.id = id;
        this.project = project;
        this.creationDate = creationDate;
        this.candidates = candidates;
        this.available = available;
    }
}