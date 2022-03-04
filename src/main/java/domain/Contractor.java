package domain;

import kernel.DomainEvent;
import use_cases.add_member.domain.Address;

import java.util.ArrayList;
import java.util.List;

public class Contractor extends Member {
    private List<Project> projects;

    protected Contractor(MemberId id, List<DomainEvent> recordedEvents) {
        super(id, recordedEvents);

    }

    public static Member of(MemberId id, List<DomainEvent> recordedEvents) {
        final Member member = new Contractor(id, new ArrayList<>());
        member.replay(recordedEvents);
        return member;
    }

    public static Member of(MemberId memberId, String lastname, String firstname, String email, String password, Address address, Role role, List<Project> projects) {
        final List<DomainEvent> recordedEvents = new ArrayList<>();
        recordedEvents.add(new CreateContractorEvent(memberId, lastname, firstname, email, password, address, role) {
        });
        Contractor contractor = new Contractor(memberId, recordedEvents);
        contractor.projects = projects;
        return  contractor;
    }
}
