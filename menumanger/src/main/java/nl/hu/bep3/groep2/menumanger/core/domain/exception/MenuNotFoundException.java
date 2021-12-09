package nl.hu.bep3.groep2.menumanger.core.domain.exception;

public class MenuNotFoundException extends RuntimeException {
	public MenuNotFoundException(String message) {
		super(message);
	}
}
