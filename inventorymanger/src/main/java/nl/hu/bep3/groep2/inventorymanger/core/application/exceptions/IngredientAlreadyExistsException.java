package nl.hu.bep3.groep2.inventorymanger.core.application.exceptions;

public class IngredientAlreadyExistsException extends Exception{
    public IngredientAlreadyExistsException() {
    }

    public IngredientAlreadyExistsException(String message) {
        super(message);
    }

    public IngredientAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public IngredientAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
