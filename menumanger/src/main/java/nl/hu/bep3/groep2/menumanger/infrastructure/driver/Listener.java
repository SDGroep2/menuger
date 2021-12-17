package nl.hu.bep3.groep2.menumanger.infrastructure.driver;

import nl.hu.bep3.groep2.menumanger.core.application.MealCommandHandler;
import nl.hu.bep3.groep2.menumanger.infrastructure.driver.messaging.event.KitchenEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public record Listener(MealCommandHandler commandHandler) {
	@RabbitListener(queues = "#{'${menu.kitchen.queue}'}")
	public void listen(KitchenEvent event) {
		switch (event.getEventKey()) {
			case "kitchen.orders.created" -> System.out.println("Order has been created");
			case "kitchen.orders.updated" -> System.out.println("Order has been updated");
		}
	}
}
