package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryCommandHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryQueryHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.UpdateIngredient;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientAlreadyExistsException;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientNotFoundException;
import nl.hu.bep3.groep2.inventorymanger.core.application.query.CheckIngredientAvailable;
import nl.hu.bep3.groep2.inventorymanger.core.application.query.GetIngredientsByName;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.MakeNewIngredient;
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
    public Ingredient getIngredientByName(@PathVariable String name) throws IngredientNotFoundException {
        return queryHandler.handle(new GetIngredientsByName(name));
    }

    @GetMapping()
    public boolean checkIngredientisAvailable(@RequestParam String name, @RequestParam int amount) throws IngredientNotFoundException {
        return queryHandler.handle(new CheckIngredientAvailable(name, amount));
    }

    @PostMapping()
    public void newIngredient(@RequestBody MakeNewIngredient command) throws IngredientAlreadyExistsException {
        commandHandler.handle(command);
    }
    @PutMapping()
    public void updateIngredient(@RequestBody UpdateIngredient command) throws IngredientNotFoundException {
        commandHandler.handle(command);
    }
}
