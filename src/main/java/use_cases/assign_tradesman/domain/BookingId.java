package use_cases.assign_tradesman.domain;

import kernel.ValueObjectID;

import java.util.Objects;

public final class BookingId implements ValueObjectID {
    private final int value;

    public BookingId(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingId bookingId = (BookingId) o;
        return value == bookingId.value;
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BookingId{" +
                "value=" + value +
                '}';
    }
}
