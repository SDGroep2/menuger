package nl.hu.bep3.groep2.menumanger.core.port.storage;

public interface IngredientRepository {
	boolean areIngredientsAvailable(String ingredient, int amount);
}
