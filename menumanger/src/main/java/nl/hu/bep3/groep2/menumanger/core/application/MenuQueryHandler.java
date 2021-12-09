package nl.hu.bep3.groep2.menumanger.core.application;

import nl.hu.bep3.groep2.menumanger.core.application.query.GetMenusByName;
import nl.hu.bep3.groep2.menumanger.core.domain.Menu;
import nl.hu.bep3.groep2.menumanger.core.domain.exception.MenuNotFoundException;
import nl.hu.bep3.groep2.menumanger.core.port.storage.MenuRepository;
import nl.hu.bep3.groep2.menumanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuQueryHandler {
	private final MenuRepository repository;
	private final Publisher publisher;

	public MenuQueryHandler(MenuRepository repository, Publisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	public List<Menu> handle(GetMenusByName getMenuByName) {
		String name = getMenuByName.getName();
		publisher.publishMessage(String.format("%s", name));
		return this.repository.findAllByName(name);
	}
}
