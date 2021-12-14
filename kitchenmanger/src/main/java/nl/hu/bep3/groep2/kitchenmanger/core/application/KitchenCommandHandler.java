package nl.hu.bep3.groep2.kitchenmanger.core.application;

import nl.hu.bep3.groep2.kitchenmanger.core.application.command.CreateNewKitchen;
import nl.hu.bep3.groep2.kitchenmanger.core.application.command.FinishedOrder;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Kitchen;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.KitchenEvent;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.exception.KitchenNotFoundException;
import nl.hu.bep3.groep2.kitchenmanger.core.port.messaging.KitchenEventPublisher;
import nl.hu.bep3.groep2.kitchenmanger.core.port.storage.KitchenRepository;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KitchenCommandHandler {
	private final KitchenRepository repository;
	private final Publisher publisher;
	private final KitchenEventPublisher kitchenEventPublisher;

	public KitchenCommandHandler(KitchenRepository repository, Publisher publisher, KitchenEventPublisher kitchenEventPublisher) {
		this.repository = repository;
		this.publisher = publisher;
		this.kitchenEventPublisher = kitchenEventPublisher;
	}

	public Kitchen handle(CreateNewKitchen newKitchen) {
		String name = newKitchen.getName();
		publisher.publishMessage(String.format("new kitchen %s was created", name));
		return repository.save(new Kitchen(name));
	}

	public Kitchen handle(FinishedOrder command) {
		Kitchen kitchen = this.getKitchenById(command.getId());

		kitchen.FinishOrder(command.getOrder());
		this.publishEventsFor(kitchen);
		this.repository.save(kitchen);

		return kitchen;
	}

	private Kitchen getKitchenById(UUID id) {
		return this.repository.findById(id)
				.orElseThrow(() -> new KitchenNotFoundException(id.toString()));
	}

	private void publishEventsFor(Kitchen kitchen) {
		List<KitchenEvent> events = kitchen.listEvents();
		events.forEach(kitchenEventPublisher::publish);
		kitchen.clearEvents();
	}
}
