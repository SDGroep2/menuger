package nl.hu.bep3.groep2.kitchenmanger.core.domain.exception;

public class OrderAlreadyExistsException extends RuntimeException {
	public OrderAlreadyExistsException(String message) {
		super(message);
	}
}
