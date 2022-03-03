package use_cases.add_member.domain;

import kernel.DomainEvent;
import kernel.Entity;

import java.util.ArrayList;
import java.util.List;

public final class Member implements Entity<MemberId> {
    private final MemberId id;
    private final List<DomainEvent> recordedEvents;

    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private Address address;
    private Role role;

    private Member(MemberId id, List<DomainEvent> recordedEvents) {
        this.id = id;
        this.recordedEvents = recordedEvents;

    }

    public static Member of(MemberId memberId, String lastname, String firstname, String email, String password, Address address, Role role) {
        final List<DomainEvent> recordedEvents = new ArrayList<>();
        recordedEvents.add(new CreateMemberEvent(memberId, lastname, firstname, email, password, address, role));
        return new Member(memberId, recordedEvents);
    }

    public static Member of(MemberId id, List<DomainEvent> recordedEvents) {
        final Member member = new Member(id, new ArrayList<>());
        member.replay(recordedEvents);
        return member;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public MemberId id() {
        return id;
    }

    @Override
    public List<DomainEvent> recordedEvents() {
        return recordedEvents;
    }

    private void replay(List<DomainEvent> recordedEvents) {
        for (DomainEvent recordedEvent : recordedEvents) {
            if (recordedEvent instanceof CreateMemberEvent) {
                applyEvent((CreateMemberEvent) recordedEvent);
            } else if (recordedEvent instanceof ModifyMemberAddressEvent) {
                applyEvent((ModifyMemberAddressEvent) recordedEvent);
            }
        }
    }

    private void applyEvent(CreateMemberEvent createMemberEvent) {
        this.lastname = createMemberEvent.getLastname();
        this.firstname = createMemberEvent.getFirstname();
        this.email = createMemberEvent.getEmail();
        this.password = createMemberEvent.getPassword();
        this.address = createMemberEvent.getAddress();
        this.role = createMemberEvent.getRole();
    }

    private void applyEvent(ModifyMemberAddressEvent modifyMemberAddressEvent) {
        this.address = modifyMemberAddressEvent.getAddress();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id.getValue() +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
