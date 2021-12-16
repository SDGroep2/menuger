package nl.hu.bep3.groep2.kitchenmanger.core.application.command;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;

import java.util.HashMap;

public class CreateNewOrder {
    private final int table;
    private final HashMap<String, Integer> meals;
    private Status status;

    public CreateNewOrder(int table, HashMap<String, Integer> meals, Status status) {
        this.table = table;
        this.meals = meals;
        this.status = status;
    }

    public int getTable() {
        return table;
    }

    public HashMap<String, Integer> getMeals() {
        return meals;
    }

    public Status getStatus() {
        return status;
    }
}
