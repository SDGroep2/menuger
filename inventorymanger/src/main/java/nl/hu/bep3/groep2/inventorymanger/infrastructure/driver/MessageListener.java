package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver;

import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryCommandHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.messaging.event.OrderPlacedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private final InventoryCommandHandler inventoryCommandHandler;

    public MessageListener(InventoryCommandHandler inventoryCommandHandler) {
        this.inventoryCommandHandler = inventoryCommandHandler;
    }

    @RabbitListener(queues = "#{'${kitchen-inventory.queue}'}")
    public void listen(OrderPlacedEvent event) {
        switch (event.getEventKey()) {
            //TODO: Stop passing ingredients here and get them from kitchen database in handler
            case "kitchen.orders.created" -> inventoryCommandHandler.handle(new OrderCreated(event.getIngredients()));
            case "kitchen.orders.updated" -> inventoryCommandHandler.handle(new OrderUpdated(event.getOrderId(),
                    event.getStatus(),
                    event.getIngredients()));
        }
    }
}
