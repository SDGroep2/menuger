package nl.hu.bep3.groep2.inventorymanger.core.application.command;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OrderUpdated {
    //TODO: Stop passing ingredients here and get them from kitchen database in handler using order id
    private UUID id;
    private String status;
    private final Map<String, Integer> ingredients;

    public OrderUpdated(UUID id, String status, Map<String, Integer> ingredients) {
        this.id = id;
        this.status = status;
        this.ingredients = ingredients;
    }
}
