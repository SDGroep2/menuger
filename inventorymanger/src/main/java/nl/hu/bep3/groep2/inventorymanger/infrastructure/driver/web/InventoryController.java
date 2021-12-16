package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryCommandHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryQueryHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.query.GetIngredientsByName;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import nl.hu.bep3.groep2.inventorymanger.core.application.query.CheckIngredientsAvailable;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.MakeNewIngredients;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.messaging.event.OrderPlacedEvent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryCommandHandler commandHandler;
    private final InventoryQueryHandler queryHandler;

    public InventoryController(InventoryCommandHandler commandHandler, InventoryQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping("/{name}")
    public Ingredient getIngredientByName(@PathVariable String name) {
        return queryHandler.handle(new GetIngredientsByName(name));
    }

    @GetMapping("/available")
    public boolean ingredientsAreAvailable(@RequestBody CheckIngredientsAvailable query){
        return queryHandler.handle(query);
    }

    @PostMapping()
    public void newIngredients(@RequestBody MakeNewIngredients command){
        commandHandler.handle(command);
    }
}
