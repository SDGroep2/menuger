package nl.hu.bep3.groep2.menumanger.core.port.messaging;

import lombok.AllArgsConstructor;
import nl.hu.bep3.groep2.menumanger.core.domain.event.MealRelatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
public class Publisher {
	private final RabbitTemplate rabbitTemplate;
	@Value("${menuger.exchange}")
	private final String messageExchange;

	public void publish(MealRelatedEvent event) {
		rabbitTemplate.convertAndSend(messageExchange, event.getEventKey(), event);
	}
}
