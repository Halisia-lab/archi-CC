package use_cases.add_member.exception;

public final class NotValidApplicationException extends RuntimeException {
    private NotValidApplicationException(String message) {
        super(message);
    }

    public static NotValidApplicationException withExistingEmail(String email) {
        return new NotValidApplicationException(email + " is already used by a member");
    }

    public static NotValidApplicationException emptyName() {
        return new NotValidApplicationException("A full name is required to apply");
    }

    public static NotValidApplicationException withPassword(String password) {
        return new NotValidApplicationException("The password has only " + password.length() + " characters, it needs at least 6");
    }

    public static NotValidApplicationException withEmail(String email) {
        return new NotValidApplicationException("The email " + email + " is incorrect");
    }

}
