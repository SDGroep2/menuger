package nl.hu.bep3.groep2.kitchenmanger.core.domain.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class OrderEvent {
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();
    private final String eventKey = "";
}
