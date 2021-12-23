package nl.hu.bep3.groep2.kitchenmanger.core.domain.event;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;

import java.time.Instant;
import java.util.UUID;

public abstract class OrderEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();
    private final String eventKey = "";
    private final Order order;


    protected OrderEvent(Order order) {
        this.order = order;
    }

    public UUID getEventId() {
        return eventId;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public String getEventKey() {
        return eventKey;
    }

    public Order getOrder() {
        return order;
    }
}
