package nl.hu.bep3.groep2.kitchenmanger.core.domain.event;

import lombok.Getter;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;

import java.util.UUID;

@Getter
public class OrderWasUpdated extends OrderEvent{
    private final String eventKey = "kitchen.orders.updated";
    private final UUID id;
    private final Status status;

    public OrderWasUpdated(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
    }
}
