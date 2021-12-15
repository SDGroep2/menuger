package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.messaging.event;

import lombok.Getter;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
public class InventoryEvent{
    private UUID eventId;
    private String eventKey;
    private Instant eventDate;
    private Map<String, Integer> ingredients;

    public InventoryEvent(UUID eventId, String eventKey, Instant eventDate, Map<String, Integer> ingredients) {
        this.eventId = eventId;
        this.eventKey = eventKey;
        this.eventDate = eventDate;
        this.ingredients = ingredients;
    }
}
