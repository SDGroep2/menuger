package nl.hu.bep3.groep2.menumanger.core.port.storage;

import java.util.Map;

public interface IngredientRepository {
	boolean areIngredientsAvailable(String ingredient, int amount);
}
