package use_cases.request_trademan.application.member;

import kernel.Command;
import use_cases.add_member.application.member.AddressDTO;
import use_cases.request_trademan.domain.Project;

import java.util.ArrayList;
import java.util.List;

public class CreateContractor extends CreateMember implements Command {

    private List<Project> projects;

    public CreateContractor(String lastname, String firstname, String email, String password, AddressDTO address) {
        super(lastname,firstname,email,password,address);
        this.projects = new ArrayList<>();
    }



    @Override
    public String toString() {
        return "Contractor{" +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
