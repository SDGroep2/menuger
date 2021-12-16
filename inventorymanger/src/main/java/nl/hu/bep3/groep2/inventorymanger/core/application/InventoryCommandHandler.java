package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.application.command.MakeNewIngredients;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryCommandHandler {
    private final InventoryRepository repository;

    public InventoryCommandHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    public void handle(MakeNewIngredients command) {
        command.getIngredients().keySet().forEach((name) -> {
            Ingredient dbIngredient = repository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("ingredient not found"));
            int newAmount = dbIngredient.getAmount() + command.getIngredients().get(name);
            dbIngredient.setAmount(newAmount);
            repository.save(dbIngredient);
        });
    }

    public void handle(OrderCreated command) {
        //TODO: replace get ingredients with call to kitchen database
        command.getIngredients().keySet().forEach((name) -> {
            Ingredient dbIngredient = repository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("ingredient not found"));
            int newAmountReserved = dbIngredient.getAmountReserved() + command.getIngredients().get(name);
            if (newAmountReserved <= dbIngredient.getAmount()) {
                dbIngredient.setAmountReserved(newAmountReserved);
                repository.save(dbIngredient);
            }
        });
    }
    public void handle(OrderUpdated command) {
        //TODO: replace get ingredients with call to kitchen database
        command.getIngredients().keySet().forEach((name) -> {
            Ingredient dbIngredient = repository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("ingredient not found"));
            int newAmount = dbIngredient.getAmount() - command.getIngredients().get(name);
            int newAmountReserved = dbIngredient.getAmount() - command.getIngredients().get(name);
            dbIngredient.setAmount(newAmount);
            dbIngredient.setAmountReserved(newAmountReserved);
            repository.save(dbIngredient);
        });

    }
}
