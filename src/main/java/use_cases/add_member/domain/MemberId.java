package use_cases.add_member.domain;

import kernel.ValueObjectID;

import java.util.Objects;

public final class MemberId implements ValueObjectID {
    private final int value;

    public MemberId(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberId memberId = (MemberId) o;
        return value == memberId.value;
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
        return "MemberId{" +
                "value=" + value +
                '}';
    }
}
