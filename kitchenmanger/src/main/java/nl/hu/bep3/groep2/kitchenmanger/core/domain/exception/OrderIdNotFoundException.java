package nl.hu.bep3.groep2.kitchenmanger.core.domain.exception;

public class OrderIdNotFoundException extends RuntimeException{
    public OrderIdNotFoundException(String message) {
        super(message);
    }
}
