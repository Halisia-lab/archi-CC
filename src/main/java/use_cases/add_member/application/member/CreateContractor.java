package use_cases.add_member.application.member;

import kernel.Command;
import domain.Role;
import domain.Project;

import java.util.ArrayList;
import java.util.List;

public class CreateContractor extends CreateMember implements Command {

    private List<Project> projects;

    public CreateContractor(String lastname, String firstname, String email, String password, AddressDTO address, Role role) {
        super(lastname,firstname,email,password,address, role);
        this.projects = new ArrayList<>();
    }



    @Override
    public String toString() {
        return "Contractor{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
