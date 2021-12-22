package nl.hu.bep3.groep2.inventorymanger.core.application.exceptions;

public class NotEnoughIngredientsException extends Exception{
    public NotEnoughIngredientsException() {
    }

    public NotEnoughIngredientsException(String message) {
        super(message);
    }

    public NotEnoughIngredientsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughIngredientsException(Throwable cause) {
        super(cause);
    }

    public NotEnoughIngredientsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
