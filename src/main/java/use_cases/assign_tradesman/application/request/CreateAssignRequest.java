package use_cases.assign_tradesman.application.request;

import domain.MemberId;
import domain.Project;
import kernel.Command;

public class CreateAssignRequest implements Command {

    public final Project project;
    public final MemberId tradesmanId;
    public final MemberId contractorId;

    public CreateAssignRequest(MemberId tradesmanId, Project project, MemberId contractorId) {
        this.tradesmanId = tradesmanId;
        this.project = project;
        this.contractorId = contractorId;
    }


    @Override
    public String toString() {
        return "CreateAssignRequest{" +
                "tradesman=" + tradesmanId.getValue() +
                ", project=" + project.getName()  +
                ", contractor=" + contractorId.getValue() +
                +
                '}';
    }
}
