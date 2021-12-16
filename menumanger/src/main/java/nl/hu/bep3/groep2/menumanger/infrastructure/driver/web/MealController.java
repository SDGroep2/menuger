package nl.hu.bep3.groep2.menumanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.menumanger.core.application.MealCommandHandler;
import nl.hu.bep3.groep2.menumanger.core.application.MenuQueryHandler;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateMeal;
import nl.hu.bep3.groep2.menumanger.core.application.query.GetMenusByName;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealController {
	private final MealCommandHandler commandHandler;
	private final MenuQueryHandler queryHandler;

	public MealController(MealCommandHandler commandHandler, MenuQueryHandler queryHandler) {
		this.commandHandler = commandHandler;
		this.queryHandler = queryHandler;
	}

	@GetMapping("/{name}")
	public List<Meal> getMenuByName(@PathVariable String name) {
		return queryHandler.handle(new GetMenusByName(name));
	}

	@PostMapping
	public Meal createMeal(@RequestBody CreateMeal newMeal) {
		return commandHandler.handle(newMeal);
	}
}
