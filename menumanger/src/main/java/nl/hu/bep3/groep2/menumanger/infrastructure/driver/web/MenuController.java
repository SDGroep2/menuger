package nl.hu.bep3.groep2.menumanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.menumanger.core.application.MenuCommandHandler;
import nl.hu.bep3.groep2.menumanger.core.application.MenuQueryHandler;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateNewMenu;
import nl.hu.bep3.groep2.menumanger.core.application.query.GetMenusByName;
import nl.hu.bep3.groep2.menumanger.core.domain.Menu;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
	private final MenuCommandHandler commandHandler;
	private final MenuQueryHandler queryHandler;

	public MenuController(MenuCommandHandler commandHandler, MenuQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@GetMapping("/{name}")
	public List<Menu> getMenuByName(@PathVariable String name) {
		return queryHandler.handle(new GetMenusByName(name));
	}

	@PostMapping
	public Menu saveMenu(@RequestBody CreateNewMenu newMenu) {
		return commandHandler.handle(newMenu);
	}
}
