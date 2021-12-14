package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.kitchenmanger.core.application.KitchenCommandHandler;
import nl.hu.bep3.groep2.kitchenmanger.core.application.KitchenQueryHandler;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.CreateNewKitchen;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.FinishedOrder;
import nl.hu.bep3.groep2.kitchenmanger.core.application.query.GetKitchenByName;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Kitchen;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web.request.OrderRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/kitchen")
public class KitchenController {
	private final KitchenCommandHandler commandHandler;
	private final KitchenQueryHandler queryHandler;

	public KitchenController(KitchenCommandHandler commandHandler, KitchenQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@GetMapping("/{name}")
	public List<Kitchen> getKitchenByName(@PathVariable String name) {
		return queryHandler.handle(new GetKitchenByName(name));
	}

	@PostMapping
	public Kitchen saveKitchen(@RequestBody CreateNewKitchen newKitchen) {
		return commandHandler.handle(newKitchen);
	}

	@PutMapping("/{id}/order")
	public Kitchen finishOrder(@PathVariable UUID id, @RequestBody OrderRequest request) {
		return this.commandHandler.handle(new FinishedOrder(id, request.order));
	}
}
