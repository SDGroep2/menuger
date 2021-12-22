package nl.hu.bep3.groep2.inventorymanger.core.application.command;

import lombok.Getter;

import java.util.Map;

@Getter
public class OrderCreated {
    //TODO: Stop passing ingredients here and get them from kitchen database in handler using order id
    private final Map<String, Integer> ingredients;

    public OrderCreated(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
