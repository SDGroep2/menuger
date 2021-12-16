package nl.hu.bep3.groep2.menumanger.core.domain.exception;

public class MealNotFoundException extends RuntimeException {
	public MealNotFoundException(String message) {
		super(message);
	}
}
