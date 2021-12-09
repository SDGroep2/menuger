package nl.hu.bep3.groep2.menumanger.core.application;

import nl.hu.bep3.groep2.menumanger.core.application.command.CreateNewMenu;
import nl.hu.bep3.groep2.menumanger.core.domain.Menu;
import nl.hu.bep3.groep2.menumanger.core.port.storage.MenuRepository;
import nl.hu.bep3.groep2.menumanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Service;

@Service
public class MenuCommandHandler {
	private final MenuRepository repository;
	private final Publisher publisher;

	public MenuCommandHandler(MenuRepository repository, Publisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	public Menu handle(CreateNewMenu newMenu) {
		String name = newMenu.getName();
		publisher.publishMessage(String.format("new menu %s was created", name));
		return repository.save(new Menu(name));
	}
}
