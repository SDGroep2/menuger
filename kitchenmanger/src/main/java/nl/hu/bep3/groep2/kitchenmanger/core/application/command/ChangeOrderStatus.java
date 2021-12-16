package nl.hu.bep3.groep2.kitchenmanger.core.application.command;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;

import java.util.UUID;

public class ChangeOrderStatus {
    private final UUID id;
    private final Status status;

    public ChangeOrderStatus(UUID id, Status status) {
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}
