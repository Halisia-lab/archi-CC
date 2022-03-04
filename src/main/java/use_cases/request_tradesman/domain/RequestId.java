package use_cases.request_tradesman.domain;

import kernel.ValueObjectID;

import java.util.Objects;

public class RequestId implements ValueObjectID {
    private final int value;

    public RequestId(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestId requestId = (RequestId) o;
        return value == requestId.value;
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
        return "RequestId{" +
                "value=" + value +
                '}';
    }
}
