package nl.hu.bep3.groep2.menumanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.menumanger.core.application.MealCommandHandler;
import nl.hu.bep3.groep2.menumanger.core.application.MenuQueryHandler;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateMeal;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateOrder;
import nl.hu.bep3.groep2.menumanger.core.application.query.GetIngredientsOfMeal;
import nl.hu.bep3.groep2.menumanger.core.application.query.GetMealByName;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	public Meal getMealByName(@PathVariable String name) {
		return queryHandler.handle(new GetMealByName(name));
	}

	@GetMapping("/{name}/ingredients")
	public Map<String, Integer> getMealIngredients(@PathVariable String name) {
		return queryHandler.handle(new GetIngredientsOfMeal(name));
	}

	@GetMapping
	public List<Meal> getAvailableMeals() {
		return queryHandler.handle();
	}

	@PostMapping
	public Meal createMeal(@RequestBody CreateMeal newMeal) {
		return commandHandler.handle(newMeal);
	}

	@PostMapping("/order")
	public CreateOrder createOrder(@RequestBody CreateOrder createOrder) {
		return commandHandler.handle(createOrder);
	}
}
