package nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.messaging;

import lombok.AllArgsConstructor;
import nl.hu.bep3.groep2.inventorymanger.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class Publisher {
	private final RabbitTemplate rabbitTemplate;
	private final String messageExchange;
	private final String routingKey;

	public String publishMessage(@RequestBody CustomMessage message) {
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessageDate(new Date());
		rabbitTemplate.convertAndSend(messageExchange, routingKey, message);
		return "Message Published";
	}
}
