package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.kitchenmanger.core.application.KitchenCommandHandler;
import nl.hu.bep3.groep2.kitchenmanger.core.application.KitchenQueryHandler;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.ChangeOrderStatus;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.CreateOrder;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
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

	@PostMapping
	public Order registerOrder(@RequestBody CreateOrder newOrder) {
		return this.commandHandler.handle(newOrder);
	}

	@PutMapping("/order/{id}/change-status")
	public Order changeOrder(@PathVariable UUID id, @RequestBody ChangeOrderStatus changeOrderStatus) {
		return this.commandHandler.handle(changeOrderStatus);
	}

	@GetMapping("/orders")
	public List<Order> getActiveOrders() {
		return queryHandler.handle();
	}
}
