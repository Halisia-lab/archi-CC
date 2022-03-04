package use_cases.add_member.exception;

import domain.MemberId;

public final class NoSuchMemberException extends RuntimeException {
    private NoSuchMemberException(String message) {
        super(message);
    }

    public static NoSuchMemberException withId(MemberId id) {
        return new NoSuchMemberException("No member found with ID " + id.getValue());
    }
}
