package use_cases.add_member.application.member;

import kernel.Command;
import use_cases.add_member.application.payment.CreatePayment;
import domain.Role;

public abstract class CreateMember implements Command {

    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final AddressDTO address;
    public CreatePayment createPayment;
    public Role role;

    public CreateMember(String lastname, String firstname, String email, String password, AddressDTO address, Role role) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createPayment = null;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                ", role=" + role + '\'' +
                '}';
    }
}
