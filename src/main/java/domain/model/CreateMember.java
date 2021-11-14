package domain.model;

public class CreateMember {

    public final String lastname;
    public final String firstname;
    public final String email;
    public final String password;
    public final Address address;

    public CreateMember(String lastname, String firstname, String email, String password, Address address) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
