package nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.messaging;

import lombok.AllArgsConstructor;
import nl.hu.bep3.groep2.inventorymanger.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class Publisher {
	private final RabbitTemplate rabbitTemplate;
	@Value("${menuger.exchange}")
	private final String messageExchange;
	@Value("${kitchen-inventory.routing-key}")
	private final String routingKey;

	public void publishMessage(String message) {
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessage(message);
		customMessage.setMessageId(UUID.randomUUID().toString());
		customMessage.setMessageDate(new Date());
		rabbitTemplate.convertAndSend(messageExchange, routingKey, customMessage);
		System.out.printf("Message %s sent!", message);
	}
}
