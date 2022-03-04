package use_cases.add_member.application.member;

import domain.MemberId;

@SuppressWarnings("all")
public class MemberDTO {
    private final MemberId id;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final String password;
    private AddressDTO address;

    public MemberDTO(MemberId id, String lastname, String firstname, String email, String password, AddressDTO address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id.getValue() +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", address=" + address + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}
