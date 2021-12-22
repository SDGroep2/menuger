package nl.hu.bep3.groep2.inventorymanger.core.application.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class CheckIngredientAvailable {
    private final String name;
    private final int amount;

    @JsonCreator
    public CheckIngredientAvailable(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
