package domain.model;

public class Address {
    private final String number;
    private final String street;
    private final String zipCode;
    private final String city;
    private final String country;

    public Address() {
        this.number = null;
        this.street = null;
        this.zipCode = null;
        this.city = null;
        this.country = null;
    }

    public Address(String number, String street, String zipCode, String city, String country) {
        this.number = number;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public static Address of() {
        return new Address();
    }

    public static Address of(String number, String street, String zipCode, String city, String country) {
        return new Address(number, street, zipCode, city, country);
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }


}
