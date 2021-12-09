package nl.hu.bep3.groep2.inventorymanger.infrastructure.driver;

import nl.hu.bep3.groep2.inventorymanger.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	@RabbitListener(queues = "#{'${messaging.queue}'}")
	public void listen(CustomMessage message) {
		System.out.println(message.getMessage());
	}
}
