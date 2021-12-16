package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class OverviewEvent {
    private final UUID eventId;
    private final String eventKey;
    private final Instant eventDate;

    public OverviewEvent(UUID eventId, String eventKey, Instant eventDate) {
        this.eventId = eventId;
        this.eventKey = eventKey;
        this.eventDate = eventDate;
    }
}

