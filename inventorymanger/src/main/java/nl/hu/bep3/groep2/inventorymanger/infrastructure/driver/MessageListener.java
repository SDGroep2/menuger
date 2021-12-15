package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver;

import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryCommandHandler;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.messaging.event.InventoryEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
	private final InventoryCommandHandler inventoryCommandHandler;

	public MessageListener(InventoryCommandHandler inventoryCommandHandler) {
		this.inventoryCommandHandler = inventoryCommandHandler;
	}

	@RabbitListener(queues = "#{'${menu-inventory.queue}'}")
	public void listen(InventoryEvent inventoryEvent) {
		inventoryCommandHandler.handle(inventoryEvent);
	}
}
