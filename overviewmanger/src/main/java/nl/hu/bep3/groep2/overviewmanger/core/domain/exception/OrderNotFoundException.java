package nl.hu.bep3.groep2.overviewmanger.core.domain.exception;

import java.util.UUID;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(UUID message) {
        super("No order with the id " + message + " was found!");
    }

}
