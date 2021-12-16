package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OverviewUpdatedOrderEvent extends OverviewEvent {
    private final UUID id;
    private final String status;

    public OverviewUpdatedOrderEvent(UUID eventId, String eventKey, Instant eventDate, UUID id, String status) {
        super(eventId, eventKey, eventDate);
        this.id = id;
        this.status = status;
    }
}
