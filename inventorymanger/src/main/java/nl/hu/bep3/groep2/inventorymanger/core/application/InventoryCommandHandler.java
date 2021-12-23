package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.application.command.MakeNewIngredient;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.UpdateIngredient;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientAlreadyExistsException;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientNotFoundException;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.NotEnoughIngredientsException;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.InventoryRepository;
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryCommandHandler {
    private final InventoryRepository inventoryRepository;
    private final MenuRepository menuRepository;


    public InventoryCommandHandler(InventoryRepository inventoryRepository, MenuRepository menuRepository) {
        this.inventoryRepository = inventoryRepository;
        this.menuRepository = menuRepository;
    }

    //Makes a new ingredient in the database
    public void handle(MakeNewIngredient command) throws IngredientAlreadyExistsException {
        for (String name : command.getIngredients().keySet()) {
            if (inventoryRepository.findByName(name).isPresent()) {
                throw new IngredientAlreadyExistsException("Ingredient with name " + name + " already exists.");
            }
            inventoryRepository.save(new Ingredient(name, command.getIngredients().get(name), 0));
        }
    }

    //Updates the amount of ingredients for ingredients with a specified name.
    public void handle(UpdateIngredient command) throws IngredientNotFoundException {
        for (String name : command.getIngredients().keySet()) {
            Ingredient dbIngredient = inventoryRepository.findByName(name)
                    .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + name));
            int newAmount = dbIngredient.getAmount() + command.getIngredients().get(name);
            dbIngredient.setAmount(newAmount);
            inventoryRepository.save(dbIngredient);
        }
        ;
    }

    //reserves ingredients of an order in the database
    public void handle(OrderCreated command) throws IngredientNotFoundException, NotEnoughIngredientsException {
        for (String meal : command.getMeals().keySet()) {
            int amountOrdered = command.getMeals().get(meal);
            Map<String, Integer> ingredients = menuRepository.getIngredientsOfMeal(meal);

            for (String ingredient : ingredients.keySet()) {
                Ingredient dbIngredient = inventoryRepository.findByName(ingredient)
                        .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + ingredient));
                int newAmountReserved = (amountOrdered * ingredients.get(ingredient)) + dbIngredient.getAmountReserved();
                if (newAmountReserved <= dbIngredient.getAmount()) {
                    dbIngredient.setAmountReserved(newAmountReserved);
                    inventoryRepository.save(dbIngredient);
                }else{
                    throw new NotEnoughIngredientsException("Not enough ingredients of type: " + ingredient + " were found.");
                }
            }
        }
    }

    //Removes ingredients of an order from database
    public void handle(OrderUpdated command) throws IngredientNotFoundException {
        for (String meal : command.getMeals().keySet()) {
            int amountOrdered = command.getMeals().get(meal);
            Map<String, Integer> ingredients = menuRepository.getIngredientsOfMeal(meal);

            for (String name : ingredients.keySet()) {
                Ingredient dbIngredient = inventoryRepository.findByName(name)
                        .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + name));
                int newAmount = dbIngredient.getAmount() - (ingredients.get(name) * amountOrdered);
                int newAmountReserved = dbIngredient.getAmount() - (ingredients.get(name) * amountOrdered);
                dbIngredient.setAmount(newAmount);
                dbIngredient.setAmountReserved(newAmountReserved);
                inventoryRepository.save(dbIngredient);
            }
        }
    }
}
