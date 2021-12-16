package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OverviewCreatedOrderEvent extends OverviewEvent {
    private final UUID orderId;

    public OverviewCreatedOrderEvent(UUID eventId, String eventKey, Instant eventDate, UUID orderId) {
        super(eventId, eventKey, eventDate);
        this.orderId = orderId;
    }



}
