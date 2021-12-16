package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.kitchenmanger.core.application.KitchenCommandHandler;
import nl.hu.bep3.groep2.kitchenmanger.core.application.KitchenQueryHandler;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.ChangeOrderStatus;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.CreateNewOrder;
import nl.hu.bep3.groep2.kitchenmanger.core.application.query.GetAllOrders;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web.request.ChangeOrderStatusRequest;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web.request.CreateOrderRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

	@GetMapping()
	public List<Order> findAllOrders() {
		return this.queryHandler.handle(new GetAllOrders(null, null));
	}

	@PostMapping
	public Order registerOrder(@Valid @RequestBody CreateOrderRequest request) {
		return this.commandHandler.handle(
				new CreateNewOrder(request.table, request.meals, request.status)
		);
	}

	@PutMapping("/order/{id}/change-status")
	public Order changeOrder(@PathVariable UUID id, @Valid @RequestBody ChangeOrderStatusRequest request) {
		return this.commandHandler.handle(new ChangeOrderStatus(id, request.status));
	}



//	@PostMapping
//	public Kitchen saveKitchen(@RequestBody CreateNewKitchen newKitchen) {
//		return commandHandler.handle(newKitchen);
//	}
//
//	@PutMapping("/{id}/order")
//	public Kitchen finishOrder(@PathVariable UUID id, @RequestBody CreateOrderRequest request) {
//		return this.commandHandler.handle(new FinishedOrder(id, request.order));
//	}
}
