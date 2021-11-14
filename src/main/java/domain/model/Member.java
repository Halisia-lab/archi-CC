package domain.model;

public class Member {
    private final MemberId id;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final String password;
    private final Address address;

    public Member(MemberId id, String lastname, String firstname, String email, String password, Address address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public static Member of(MemberId id, String lastname, String firstname, String email, String password, Address address) {
        return new Member(id, lastname, firstname, email, password, address);
    }

    public MemberId getId() {
        return id;
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
}
