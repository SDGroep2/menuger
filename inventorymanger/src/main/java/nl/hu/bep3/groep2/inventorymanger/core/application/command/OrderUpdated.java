package nl.hu.bep3.groep2.inventorymanger.core.application.command;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OrderUpdated {
    private final UUID id;
    private final String status;
    private final Map<String, Integer> meals;

    public OrderUpdated(UUID id, String status, Map<String, Integer> meals) {
        this.id = id;
        this.status = status;
        this.meals = meals;
    }
}
