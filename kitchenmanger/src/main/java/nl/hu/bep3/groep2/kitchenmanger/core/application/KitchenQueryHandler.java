package nl.hu.bep3.groep2.kitchenmanger.core.application;

import nl.hu.bep3.groep2.kitchenmanger.core.application.query.GetOrder;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.exception.OrderIdNotFoundException;
import nl.hu.bep3.groep2.kitchenmanger.core.port.storage.KitchenRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitchenQueryHandler {
	private final KitchenRepository kitchenRepository;

	public KitchenQueryHandler(KitchenRepository kitchenRepository) {
		this.kitchenRepository = kitchenRepository;
	}

	public List<Order> handle() {
		List<Order> activeOrders = new ArrayList<>();
		List<Order> orders = kitchenRepository.findAll();
		for (Order order : orders) {
			if (order.getStatus() != Status.FINISHED) {
				activeOrders.add(order);
			}
		}
		return activeOrders;
	}

	public Order handle(GetOrder order) {
		return kitchenRepository.findById(order.id()).orElseThrow(() -> new OrderIdNotFoundException(order.id().toString()));
	}
}