package nl.hu.bep3.groep2.overviewmanger.core.application.command;

import lombok.Getter;
import nl.hu.bep3.groep2.overviewmanger.core.domain.Status;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OrderUpdated {
    private UUID id;
    private String status;
    private Instant updatedAt;

    public OrderUpdated(UUID id, String status, Instant updatedAt) {
        this.id = id;
        this.status = status;
        this.updatedAt = updatedAt;
    }
}
