package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryCommandHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryQueryHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.query.GetIngredientsByName;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
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

}
