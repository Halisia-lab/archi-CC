package use_cases.request_trademan.application.request;

import kernel.Command;
import use_cases.add_member.domain.MemberId;
import use_cases.request_trademan.application.member.CreateTradesman;
import use_cases.request_trademan.domain.Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateRequest implements Command {
    public Project project;
    public LocalDate creationDate;
    public boolean available;
    public MemberId contractorId;
    public List<CreateTradesman> candidates;


    public CreateRequest(Project project, MemberId contractorId) {
        this.project = project;
        this.creationDate = LocalDate.now();
        this.available = false;
        this.candidates = new ArrayList<>();
        this.contractorId = contractorId;
    }

    public Project getProject() {
        return project;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public List<CreateTradesman> getCandidates() {
        return candidates;
    }

    @Override
    public String toString() {
        return "Request{" +
                ", project='" + project.getName() + '\'' +
                ", creationDate=" + creationDate + '\'' +
                ", available=" + available + '\'' +
                ", candidates=" + candidates.size() + '\'' +
                '}';
    }
}
