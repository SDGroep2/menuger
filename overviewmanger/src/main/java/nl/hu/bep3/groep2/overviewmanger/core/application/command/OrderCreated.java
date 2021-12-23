package nl.hu.bep3.groep2.overviewmanger.core.application.command;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OrderCreated {
    private UUID id;
    private Instant createdAt;

    public OrderCreated(UUID id, Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }
}
