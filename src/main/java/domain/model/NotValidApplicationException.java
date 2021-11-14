package domain.model;

public class NotValidApplicationException extends RuntimeException {
    private NotValidApplicationException(String message) {
        super(message);
    }

    public static NotValidApplicationException emailAlreadyExists() {
        return new NotValidApplicationException("This email is already used by a member");
    }

    public static NotValidApplicationException emptyName() {
        return new NotValidApplicationException("A full name is required to apply");
    }

    public static NotValidApplicationException tooShortPassword() {
        return new NotValidApplicationException("The password needs at least 6 characters");
    }

    public static NotValidApplicationException incorrectEmail() {
        return new NotValidApplicationException("The email is incorrect");
    }

}
