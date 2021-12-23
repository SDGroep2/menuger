package nl.hu.bep3.groep2.inventorymanger.core.ports.storage;

import java.util.Map;
import java.util.UUID;

public interface KitchenRepository {
	Map<String, Integer> getMealsForOrder(UUID id);
}
