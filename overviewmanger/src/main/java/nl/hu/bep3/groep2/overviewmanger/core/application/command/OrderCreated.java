package nl.hu.bep3.groep2.overviewmanger.core.application.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderCreated {
    private UUID id;

    public OrderCreated(UUID id) {
        this.id = id;
    }
}
