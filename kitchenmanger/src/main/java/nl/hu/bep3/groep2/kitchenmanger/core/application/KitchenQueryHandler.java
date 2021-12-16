package nl.hu.bep3.groep2.kitchenmanger.core.application;

import nl.hu.bep3.groep2.kitchenmanger.core.application.query.GetAllOrders;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import nl.hu.bep3.groep2.kitchenmanger.core.port.storage.OrderRepository;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driven.messaging.Publisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenQueryHandler {
	private final OrderRepository orderRepository;
	private final Publisher publisher;

	public KitchenQueryHandler(OrderRepository orderRepository, Publisher publisher) {
		this.orderRepository = orderRepository;
		this.publisher = publisher;
	}

	public List<Order> handle(GetAllOrders query) {
		Sort sort = createSort(query.getOrderBy(), query.getDirection());
		return this.orderRepository.findAll(sort);
	}

	private Sort createSort(String orderBy, String direction) {
		Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

		if (direction.equals("desc")) {
			sort = sort.descending();
		}

		return sort;
	}
}
