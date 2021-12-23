package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver;

import nl.hu.bep3.groep2.kitchenmanger.core.application.KitchenCommandHandler;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.messaging.event.KitchenEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public record Listener(KitchenCommandHandler kitchenCommandHandler) {
    @RabbitListener(queues = "#{'${menu.kitchen.queue}'}")
    public void listen(KitchenEvent event) {
        switch (event.getEventKey()) {
            case "menu.orders.created" -> System.out.println("Order has been created");
        }
    }
}
