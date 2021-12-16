package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.application.query.CheckIngredientsAvailable;
import nl.hu.bep3.groep2.inventorymanger.core.application.query.GetIngredientsByName;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryQueryHandler {
    private final InventoryRepository repository;

    public InventoryQueryHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    public Ingredient handle(GetIngredientsByName query) {
        return repository.findByName(query.getName()).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public boolean handle(CheckIngredientsAvailable query) {
        boolean ingredientsAvailable = true;
        for (String name : query.getIngredients().keySet()) {
            Ingredient dbIngredient = repository.findByName(name).orElseThrow(() ->  new RuntimeException("ingredient not found"));
            if (dbIngredient.getAmount() < query.getIngredients().get(name)) {
                ingredientsAvailable = false;
            }
        }
        return ingredientsAvailable;
    }

}
