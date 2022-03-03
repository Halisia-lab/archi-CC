package use_cases.request_trademan.application.member;

import kernel.Command;
import use_cases.add_member.application.member.AddressDTO;
import use_cases.add_member.application.payment.CreatePayment;
import use_cases.add_member.domain.Role;

public abstract class CreateMember implements Command {

    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final AddressDTO address;

    public CreateMember(String lastname, String firstname, String email, String password, AddressDTO address) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
