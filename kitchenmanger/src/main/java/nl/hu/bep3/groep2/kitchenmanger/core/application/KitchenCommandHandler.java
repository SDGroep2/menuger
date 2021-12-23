package nl.hu.bep3.groep2.kitchenmanger.core.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.ChangeOrderStatus;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.CreateOrder;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.OrderWasCreated;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.OrderWasUpdated;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.exception.OrderIdNotFoundException;
import nl.hu.bep3.groep2.kitchenmanger.core.port.storage.KitchenRepository;
import nl.hu.bep3.groep2.kitchenmanger.core.port.messaging.Publisher;
import org.springframework.stereotype.Service;


@Service
public class KitchenCommandHandler {
	private final KitchenRepository orderRepository;
	private final Publisher publisher;

	@JsonCreator
	public KitchenCommandHandler(KitchenRepository kitchenRepository, Publisher publisher) {
		this.orderRepository = kitchenRepository;
		this.publisher = publisher;
	}

	public Order handle(CreateOrder newOrder) {
		Order order = new Order(newOrder.id(), newOrder.table(), newOrder.meals(), newOrder.status());
		orderRepository.save(order);
		publisher.publish(new OrderWasCreated(order));
		return order;
	}

	public Order handle(ChangeOrderStatus changeOrder) {
		Order order = orderRepository.findById(changeOrder.id()).orElseThrow(() -> new OrderIdNotFoundException("Id not found"));
		orderRepository.save(order);
		publisher.publish(new OrderWasUpdated(order));
		return order;
	}

}