package service.exceptions;

public class SsnAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;
    public SsnAlreadyExistsException(String ssn) {
        super("SSN " + ssn + " already exists.");
    }
}
