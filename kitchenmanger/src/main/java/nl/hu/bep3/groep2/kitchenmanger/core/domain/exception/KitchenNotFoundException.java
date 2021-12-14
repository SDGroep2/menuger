package nl.hu.bep3.groep2.kitchenmanger.core.domain.exception;

public class KitchenNotFoundException extends RuntimeException {
	public KitchenNotFoundException(String message) {
		super(message);
	}
}
