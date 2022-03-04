package use_cases.add_member.application.member;

import kernel.Command;
import domain.Role;
import domain.ProjectLocalization;
import domain.ProjectSkills;

import java.util.List;

public class CreateTradesman extends CreateMember implements Command {

    private List<ProjectSkills> skills;
    private List<ProjectLocalization> availabilityZones;
    private Number dailyTaxMin;

    public CreateTradesman(String lastname, String firstname, String email, String password, AddressDTO address, List<ProjectSkills> skills, List<ProjectLocalization> availabilityZones, Number dailyTaxMin, Role role) {
       super(lastname, firstname, email, password, address, role);
        this.skills = skills;
        this.availabilityZones = availabilityZones;
        this.dailyTaxMin = dailyTaxMin;

    }

    public List<ProjectSkills> getSkills() {
        return skills;
    }

    public List<ProjectLocalization> getAvailabilityZones() {
        return availabilityZones;
    }

    public Number getDailyTaxMin() {
        return dailyTaxMin;
    }

    public void setSkills(List<ProjectSkills> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Tradesman{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
