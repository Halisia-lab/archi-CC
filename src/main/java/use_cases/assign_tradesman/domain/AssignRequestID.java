package use_cases.assign_tradesman.domain;

import kernel.ValueObjectID;

import java.util.Objects;

public class AssignRequestID implements ValueObjectID {
    private final int value;

    public AssignRequestID(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignRequestID assignRequestID = (AssignRequestID) o;
        return value == assignRequestID.value;
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
        return "AssignRequestID{" +
                "value=" + value +
                '}';
    }
}
