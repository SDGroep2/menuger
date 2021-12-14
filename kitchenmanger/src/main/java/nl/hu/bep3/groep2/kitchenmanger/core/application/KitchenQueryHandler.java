package nl.hu.bep3.groep2.kitchenmanger.core.application;

import nl.hu.bep3.groep2.kitchenmanger.core.application.query.GetKitchenByName;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Kitchen;
import nl.hu.bep3.groep2.kitchenmanger.core.port.storage.KitchenRepository;
import nl.hu.bep3.groep2.kitchenmanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenQueryHandler {
	private final KitchenRepository repository;
	private final Publisher publisher;

	public KitchenQueryHandler(KitchenRepository repository, Publisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	public List<Kitchen> handle(GetKitchenByName getKitchenByName) {
		String name = getKitchenByName.getName();
		publisher.publishMessage(String.format("%s", name));
		return this.repository.findByName(name);
	}
}
