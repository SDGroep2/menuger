package nl.hu.bep3.groep2.kitchenmanger.core.port.messaging;

import lombok.AllArgsConstructor;
import nl.hu.bep3.groep2.kitchenmanger.CustomMessage;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class Publisher {
	private final RabbitTemplate rabbitTemplate;
	@Value("${menuger.exchange}")
	private final String messageExchange;

	public void publish(OrderEvent event) {
		rabbitTemplate.convertAndSend(messageExchange, event.getEventKey(), event);
	}
}
