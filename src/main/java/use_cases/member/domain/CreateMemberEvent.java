package use_cases.member.domain;

import kernel.DomainEvent;
import use_cases.member.application.payment.CreatePayment;

public class CreateMemberEvent implements DomainEvent {

    private final MemberId id;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final String password;
    private final Address address;
    private final CreatePayment createPayment;

    public CreateMemberEvent(MemberId id, String lastname, String firstname, String email, String password, Address address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createPayment = null;
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
}
