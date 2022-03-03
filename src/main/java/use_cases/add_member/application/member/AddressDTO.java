package use_cases.add_member.application.member;

public class AddressDTO {
    public final String street;
    public final String city;

    public AddressDTO(String street, String city) {
        this.street = street;
        this.city = city;
    }
}
