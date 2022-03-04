package use_cases.add_member.domain;

public final class Address {
    private final String street;
    private final String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}
