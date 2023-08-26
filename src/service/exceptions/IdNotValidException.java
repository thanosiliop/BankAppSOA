package service.exceptions;

import model.Account;

public class IdNotValidException extends Exception {
    private static final long serialVersionUID = 1L;

    public IdNotValidException(long id) {
        super("ID " + id + " is not valid.");
    }
}
