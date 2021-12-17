package nl.hu.bep3.groep2.inventorymanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import java.util.Map;

@Getter
public class MakeNewIngredients {
    private Map<String, Integer> ingredients;

    @JsonCreator
    public MakeNewIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
