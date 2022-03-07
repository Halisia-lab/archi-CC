package domain;

import kernel.DomainEvent;
import use_cases.add_member.domain.Address;

import java.util.List;

public class CreateTradesmanEvent extends CreateMemberEvent implements DomainEvent {

    private List<ProjectSkills> skills;
    private List<ProjectLocalization> availabilityZones;
    private Number dailyTaxMin;

    public CreateTradesmanEvent(MemberId id, String lastname, String firstname, String email, String password, Address address, Role role) {
        super(id, lastname, firstname, email, password, address, role);

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
}
