package nl.hu.bep3.groep2.menumanger.core.domain.exception;

public class MealAlreadyExistsException extends RuntimeException {
	public MealAlreadyExistsException(String message) {
		super(message);
	}
}
