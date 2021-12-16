package nl.hu.bep3.groep2.kitchenmanger.core.application;

import nl.hu.bep3.groep2.kitchenmanger.core.application.command.ChangeOrderStatus;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.CreateNewOrder;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.OrderEvent;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.exception.OrderIdNotFoundException;
import nl.hu.bep3.groep2.kitchenmanger.core.port.messaging.OrderEventPublisher;
import nl.hu.bep3.groep2.kitchenmanger.core.port.storage.OrderRepository;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KitchenCommandHandler {
	private final OrderRepository orderRepository;
	private final OrderEventPublisher orderEventPublisher;
	private final Publisher publisher;

	public KitchenCommandHandler(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher, Publisher publisher) {
		this.orderRepository = orderRepository;
		this.orderEventPublisher = orderEventPublisher;
		this.publisher = publisher;
	}

	public Order handle(CreateNewOrder command) {
		Order order = new Order(command.getTable(), command.getMeals(), command.getStatus());

		this.publishEventsFor(order);
		this.orderRepository.save(order);

		return order;
	}

	public Order handle(ChangeOrderStatus command) {
		Order order = this.getOrderById(command.getId());

		order.changeOrderStatus(command.getStatus());
		this.publishEventsFor(order);
		this.orderRepository.save(order);

		return order;
	}

	private Order getOrderById(UUID id) {
		return this.orderRepository.findById(id)
				.orElseThrow(() -> new OrderIdNotFoundException(id.toString()));
	}

	private void publishEventsFor(Order order) {
		List<OrderEvent> events = order.listEvents();
		events.forEach(orderEventPublisher::publish);
		order.clearEvents();
	}


}