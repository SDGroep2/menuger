package nl.hu.bep3.groep2.inventorymanger.core.ports.storage;

import java.util.Map;

public interface MenuRepository {
    Map<String, Integer> getIngredientsOfMeal(String meal);
}
