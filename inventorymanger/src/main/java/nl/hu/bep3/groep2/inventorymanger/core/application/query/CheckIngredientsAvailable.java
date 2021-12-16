package nl.hu.bep3.groep2.inventorymanger.core.application.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Map;

@Getter
public class CheckIngredientsAvailable {
    private Map<String, Integer> ingredients;

    @JsonCreator
    public CheckIngredientsAvailable(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
