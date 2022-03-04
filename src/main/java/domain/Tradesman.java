package domain;

import kernel.DomainEvent;
import use_cases.add_member.domain.Address;

import java.util.ArrayList;
import java.util.List;

public class Tradesman extends Member{
    private List<ProjectSkills> skills;
    private List<ProjectLocalization> availabilityZones;
    private Number dailyTaxMin;

    protected Tradesman(MemberId id, List<DomainEvent> recordedEvents) {
        super(id, recordedEvents);
    }

    public static Tradesman of(MemberId id, List<DomainEvent> recordedEvents) {
        final Tradesman member = new Tradesman(id, new ArrayList<>());
        member.replay(recordedEvents);
        return member;
    }

    public static Tradesman of(MemberId memberId, String lastname, String firstname, String email, String password, Address address, Role role, List<ProjectSkills> skills, List<ProjectLocalization> availabilityZones, Number dailyTaxMin) {
        final List<DomainEvent> recordedEvents = new ArrayList<>();
        recordedEvents.add(new CreateTradesmanEvent(memberId, lastname, firstname, email, password, address, role) {
        });
        Tradesman tradesman = new Tradesman(memberId, recordedEvents);
        tradesman.skills = skills;
        tradesman.availabilityZones = availabilityZones;
        tradesman.dailyTaxMin = dailyTaxMin;
        return tradesman;
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
