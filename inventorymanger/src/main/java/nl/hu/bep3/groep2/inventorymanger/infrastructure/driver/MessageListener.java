package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver;

import nl.hu.bep3.groep2.inventorymanger.core.application.InventoryCommandHandler;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.inventorymanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.IngredientNotFoundException;
import nl.hu.bep3.groep2.inventorymanger.core.application.exceptions.NotEnoughIngredientsException;
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
    public void listen(OrderPlacedEvent event) throws IngredientNotFoundException, NotEnoughIngredientsException {
        switch (event.getEventKey()) {
            case "kitchen.orders.created" -> inventoryCommandHandler.handle(new OrderCreated(event.getMeals()));
            case "kitchen.orders.updated" -> inventoryCommandHandler.handle(new OrderUpdated(event.getId(),
                    event.getStatus(),
                    event.getMeals()));
        }
    }
}
