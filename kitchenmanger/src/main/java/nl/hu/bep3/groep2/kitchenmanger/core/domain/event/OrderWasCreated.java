package nl.hu.bep3.groep2.kitchenmanger.core.domain.event;

import lombok.Getter;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;

@Getter
public class OrderWasCreated extends OrderEvent{
    private final String eventKey = "kitchen.order.created";

    public OrderWasCreated(Order order) {
        super(order);
    }
}
