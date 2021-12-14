package nl.hu.bep3.groep2.kitchenmanger.core.application.command;

import java.util.UUID;

public class FinishedOrder {
    private final UUID id;
    private final String order;

    public FinishedOrder(UUID id, String order) {
        this.id = id;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public String getOrder() {
        return order;
    }
}
