package nl.hu.bep3.groep2.inventorymanger.core.application.command;

import lombok.Getter;

import java.util.Map;

@Getter
public class UpdateIngredient {
    private Map<String, Integer> ingredients;

    public UpdateIngredient(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
