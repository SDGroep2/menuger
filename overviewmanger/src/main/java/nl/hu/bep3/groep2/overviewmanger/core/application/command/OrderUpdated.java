package nl.hu.bep3.groep2.overviewmanger.core.application.command;

import lombok.Getter;
import nl.hu.bep3.groep2.overviewmanger.core.domain.Status;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OrderUpdated {
    private final UUID id;
    private final Status status;
    private final Instant updatedAt;

    public OrderUpdated(UUID id, Status status, Instant updatedAt) {
        this.id = id;
        this.status = status;
        this.updatedAt = updatedAt;
    }
}
