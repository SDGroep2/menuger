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
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.KitchenRepository;
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryCommandHandler {
    private final InventoryRepository inventoryRepository;
    private final MenuRepository menuRepository;
    private final KitchenRepository kitchenRepository;


    public InventoryCommandHandler(InventoryRepository inventoryRepository, MenuRepository menuRepository,
                                   KitchenRepository kitchenRepository) {
        this.inventoryRepository = inventoryRepository;
        this.menuRepository = menuRepository;
        this.kitchenRepository = kitchenRepository;
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
    }

    //reserves ingredients of an order in the database
    @Transactional
    public void handle(OrderCreated command) throws IngredientNotFoundException, NotEnoughIngredientsException {
        //get list of requested ingredients
        Map<String, Integer> usedIngredients = new HashMap<>();
        for (String meal : command.getMeals().keySet()) {
            Map<String, Integer> ingredients = menuRepository.getIngredientsOfMeal(meal);
            for (String ingredient : ingredients.keySet()) {
                int amountNew = ingredients.get(ingredient) * command.getMeals().get(meal);
                int count = usedIngredients.containsKey(ingredient) ?
                        usedIngredients.get(ingredient) + amountNew : amountNew;
                usedIngredients.put(ingredient, count);
            }
        }
        //check availability of requested ingredients
        List<Ingredient> dbIngredients = new ArrayList<>();
        for (String ingredient : usedIngredients.keySet()){
            Ingredient dbIngredient = inventoryRepository.findByName(ingredient)
                    .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + ingredient));
            int reservedAmount = usedIngredients.get(ingredient);
            if (reservedAmount <= dbIngredient.getAmount()) {
                dbIngredient.setAmountReserved(reservedAmount);
                dbIngredients.add(dbIngredient);
            } else {
                throw new NotEnoughIngredientsException("Not enough ingredients of type: " + ingredient + " were found.");
            }
        }
        inventoryRepository.saveAll(dbIngredients);
    }

    //Removes ingredients of an order from database
    @Transactional
    public void handle(OrderUpdated command) throws IngredientNotFoundException {
        //get list of requested ingredients
        Map<String, Integer> usedIngredients = new HashMap<>();
        Map<String, Integer> mealsInOrder = kitchenRepository.getMealsForOrder(command.getId());
        for (String meal : mealsInOrder.keySet()) {
            Map<String, Integer> ingredients = menuRepository.getIngredientsOfMeal(meal);
            for (String ingredient : ingredients.keySet()) {
                int amountNew = ingredients.get(ingredient) * mealsInOrder.get(meal);
                int count = usedIngredients.containsKey(ingredient) ?
                        usedIngredients.get(ingredient) + amountNew : amountNew;
                usedIngredients.put(ingredient, count);
            }
        }
        //checks existence of requested ingredients
        List<Ingredient> dbIngredients = new ArrayList<>();
        for (String ingredient : usedIngredients.keySet()){
            Ingredient dbIngredient = inventoryRepository.findByName(ingredient)
                    .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + ingredient));
            int usedAmount = usedIngredients.get(ingredient);
            int newAmount = dbIngredient.getAmount() - usedAmount;
            int newAmountReserved = dbIngredient.getAmount() - usedAmount;
            dbIngredient.setAmount(newAmount);
            dbIngredient.setAmountReserved(newAmountReserved);
            dbIngredients.add(dbIngredient);
        }
        inventoryRepository.saveAll(dbIngredients);
    }
}
