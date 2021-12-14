package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver;

import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryCommandHandler;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.messaging.event.InventoryEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
//	private InventoryCommandHandler inventoryCommandHandler;

	public MessageListener() {
//		this.inventoryCommandHandler = inventoryCommandHandler;
	}

	@RabbitListener(queues = "#{'${menu-inventory.queue}'}")
	public void listen() {
//		inventoryCommandHandler.handle(inventoryEvent);
	}
}
