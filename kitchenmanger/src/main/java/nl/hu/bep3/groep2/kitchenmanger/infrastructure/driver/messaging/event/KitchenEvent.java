package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.messaging.event;

import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
public class KitchenEvent {
    private final UUID eventId;
    private final Instant eventDate;
    private final String eventKey;
    private final int table;
    private final List<String> meals;

    public KitchenEvent(UUID eventId, Instant eventDate, String eventKey, int table, List<String> meals) {
        this.eventId = eventId;
        this.eventDate = eventDate;
        this.eventKey = eventKey;
        this.table = table;
        this.meals = meals;
    }
}
