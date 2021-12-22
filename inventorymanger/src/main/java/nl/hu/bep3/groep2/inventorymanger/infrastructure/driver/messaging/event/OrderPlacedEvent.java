package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.messaging.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
public class OrderPlacedEvent {
    private final UUID eventId;
    private final Instant eventDate;
    private final String eventKey;
    private final UUID orderId;
    private final Map<String, Integer> ingredients;
    private final String status;

    @JsonCreator
    public OrderPlacedEvent(UUID eventId, Instant eventDate, String eventKey, UUID orderId, Map<String, Integer> ingredients, String status) {
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.eventKey = eventKey;
        this.orderId = orderId;
        this.ingredients = ingredients;
        this.status = status;
    }
}
