package domain;

import domain.MemberId;
import domain.Role;
import kernel.DomainEvent;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.domain.Address;

public abstract class CreateMemberEvent implements DomainEvent {

    private final MemberId id;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final String password;
    private final Address address;
    private final CreatePayment createPayment;
    private final Role role;

    public CreateMemberEvent(MemberId id, String lastname, String firstname, String email, String password, Address address, Role role) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createPayment = null;
        this.role = role;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public CreatePayment getCreatePayment() {
        return createPayment;
    }

    public Role getRole() {
        return role;
    }
}
