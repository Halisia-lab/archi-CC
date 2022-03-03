package use_cases.request_trademan.application.member;

import kernel.Command;
import use_cases.add_member.application.member.AddressDTO;
import use_cases.add_member.domain.Role;

import java.util.ArrayList;
import java.util.List;

public class CreateTradesman extends CreateMember implements Command {

    public Role role;
    private List<String> skills;
    private List<String> availabilityZones;
    private Number dailyTaxMin;

    public CreateTradesman(String lastname, String firstname, String email, String password, AddressDTO address) {
        super(lastname,firstname,email,password,address);
        this.skills = new ArrayList<>();
        this.availabilityZones = new ArrayList<>();
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getAvailabilityZones() {
        return availabilityZones;
    }

    public void setAvailabilityZones(List<String> availabilityZones) {
        this.availabilityZones = availabilityZones;
    }

    public Number getDailyTaxMin() {
        return dailyTaxMin;
    }

    public void setDailyTaxMin(Number dailyTaxMin) {
        this.dailyTaxMin = dailyTaxMin;
    }

    @Override
    public String toString() {
        return "Trademan{" +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
