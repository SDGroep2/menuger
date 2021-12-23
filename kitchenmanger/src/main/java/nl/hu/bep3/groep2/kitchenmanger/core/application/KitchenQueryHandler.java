package nl.hu.bep3.groep2.kitchenmanger.core.application;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;
import nl.hu.bep3.groep2.kitchenmanger.core.port.storage.KitchenRepository;
import nl.hu.bep3.groep2.kitchenmanger.core.port.messaging.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenQueryHandler {
	private final KitchenRepository kitchenRepository;
	private final Publisher publisher;

	public KitchenQueryHandler(KitchenRepository kitchenRepository, Publisher publisher) {
		this.kitchenRepository = kitchenRepository;
		this.publisher = publisher;
	}

	public List<Order> handle() {
		List<Order> activeOrders = null;
		List<Order> orders = kitchenRepository.findAll();
		for (Order order : orders) {
			if (order.getStatus() == Status.ORDERED) {
				activeOrders.add(order);
			}
		}
		return activeOrders;
	}
}
