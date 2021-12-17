package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientNotFoundException;
import nl.hu.bep3.groep2.inventorymanger.core.application.query.CheckIngredientAvailable;
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

    public Ingredient handle(GetIngredientsByName query) throws IngredientNotFoundException {
        return repository.findByName(query.getName()).
                orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + query.getName()));
    }

    //gives true if the specified amount of the specified ingredient is available, gives false otherwise.
    public boolean handle(CheckIngredientAvailable query) throws IngredientNotFoundException {
        Ingredient dbIngredient = repository.findByName(query.getName())
                .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + query.getName()));
        return (dbIngredient.getAmount() - dbIngredient.getAmountReserved()) >=  query.getAmount();
    }

}
