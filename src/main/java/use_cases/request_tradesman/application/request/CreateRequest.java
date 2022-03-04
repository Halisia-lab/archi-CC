package use_cases.request_tradesman.application.request;

import kernel.Command;
import domain.MemberId;
import domain.Project;

import java.time.LocalDate;

public class CreateRequest implements Command {
    public Project project;
    public LocalDate creationDate;
    public boolean available;
    public MemberId contractorId;


    public CreateRequest(Project project, MemberId contractorId) {
        this.project = project;
        this.creationDate = LocalDate.now();
        this.available = false;
        this.contractorId = contractorId;
    }

    public Project getProject() {
        return project;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }


    @Override
    public String toString() {
        return "Request{" +
                "project='" + project.getName() + '\'' +
                ", creationDate=" + creationDate + '\'' +
                ", available=" + available + '\'' +
                '}';
    }
}
