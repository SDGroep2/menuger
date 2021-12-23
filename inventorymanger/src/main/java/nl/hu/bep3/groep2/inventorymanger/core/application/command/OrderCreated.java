package nl.hu.bep3.groep2.inventorymanger.core.application.command;

import lombok.Getter;

import java.util.Map;

@Getter
public class OrderCreated {
    private final Map<String, Integer> meals;

    public OrderCreated(Map<String, Integer> meals) {
        this.meals = meals;
    }
}
