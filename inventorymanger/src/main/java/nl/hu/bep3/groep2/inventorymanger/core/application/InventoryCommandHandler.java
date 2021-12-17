package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.application.command.MakeNewIngredient;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.UpdateIngredient;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientAlreadyExistsException;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientNotFoundException;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryCommandHandler {
    private final InventoryRepository repository;

    public InventoryCommandHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    //Makes a new ingredient in the database
    public void handle(MakeNewIngredient command) throws IngredientAlreadyExistsException {
        for (String name : command.getIngredients().keySet()) {
            if (repository.findByName(name).isPresent()) {
                throw new IngredientAlreadyExistsException("Ingredient with name " + name + " already exists.");
            }
            repository.save(new Ingredient(name, command.getIngredients().get(name), 0));
        }
        ;
    }

    //Updates the amount of ingredients for ingredients with a specified name.
    public void handle(UpdateIngredient command) throws IngredientNotFoundException {
        for (String name : command.getIngredients().keySet()) {
            Ingredient dbIngredient = repository.findByName(name)
                    .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + name));
            int newAmount = dbIngredient.getAmount() + command.getIngredients().get(name);
            dbIngredient.setAmount(newAmount);
            repository.save(dbIngredient);
        }
        ;
    }

    //reserves ingredients of an order in the database
    public void handle(OrderCreated command) throws IngredientNotFoundException {
        //TODO: replace get ingredients with call to kitchen database
        for (String name : command.getIngredients().keySet()) {
            Ingredient dbIngredient = repository.findByName(name)
                    .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + name));
            int newAmountReserved = dbIngredient.getAmountReserved() + command.getIngredients().get(name);
            if (newAmountReserved <= dbIngredient.getAmount()) {
                dbIngredient.setAmountReserved(newAmountReserved);
                repository.save(dbIngredient);
            }
        }
    }

    //Removes ingredients of an order from database
    public void handle(OrderUpdated command) throws IngredientNotFoundException {
        //TODO: replace get ingredients with call to kitchen database
        for (String name : command.getIngredients().keySet()) {
            Ingredient dbIngredient = repository.findByName(name)
                    .orElseThrow(() -> new IngredientNotFoundException("Something went wrong getting the following ingredient: " + name));
            int newAmount = dbIngredient.getAmount() - command.getIngredients().get(name);
            int newAmountReserved = dbIngredient.getAmount() - command.getIngredients().get(name);
            dbIngredient.setAmount(newAmount);
            dbIngredient.setAmountReserved(newAmountReserved);
            repository.save(dbIngredient);
        }
        ;

    }
}
