package use_cases.add_member.domain;

import kernel.ValueObjectID;

import java.util.Objects;

public class PaymentId implements ValueObjectID {
    private final int value;

    public PaymentId(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return  value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentId paymentId = (PaymentId) o;
        return value == paymentId.value;
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
        return "PaymentId{" +
                "value=" + value +
                '}';
    }

}
