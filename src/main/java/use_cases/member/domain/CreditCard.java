package use_cases.member.domain;

import java.time.LocalDate;

public final class CreditCard {

    private final String holderName;
    private final String number;
    private final LocalDate expirationDate;

    public CreditCard(String holderName, String number, LocalDate expirationDate) {
        this.holderName = holderName;
        this.number = number;
        this.expirationDate = expirationDate;
    }

    public static CreditCard of(String holderName, String number, LocalDate expirationDate) {
        return new CreditCard(holderName, number, expirationDate);
    }

    public String getHolderName() {
        return holderName;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
