package domain;

import kernel.DomainEvent;
import use_cases.add_member.domain.Address;

import java.util.List;

public class CreateContractorEvent extends CreateMemberEvent implements DomainEvent {

    private List<Project> projects;

    public CreateContractorEvent(MemberId id, String lastname, String firstname, String email, String password, Address address, Role role) {
        super(id, lastname, firstname, email, password, address, role);
        this.projects = projects;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
