package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OverviewOrderEvent {
    private final UUID eventId;
    private final String eventKey;
    private final Instant eventDate;
    private final UUID id;
    private final String status;

    @JsonCreator
    public OverviewOrderEvent(UUID eventId, String eventKey, Instant eventDate, UUID id, String status) {
        this.eventId = eventId;
        this.eventKey = eventKey;
        this.eventDate = eventDate;
        this.id = id;
        this.status = status;
    }
}
