package use_cases.member.application.member;

import kernel.Command;
import use_cases.member.application.payment.CreatePayment;

public final class CreateMember implements Command {

    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final AddressDTO address;
    public CreatePayment createPayment;

    public CreateMember(String lastname, String firstname, String email, String password, AddressDTO address) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createPayment = null;
    }
}
