package nl.hu.bep3.groep2.menumanger.core.application;

import nl.hu.bep3.groep2.menumanger.core.application.query.GetMealByName;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import nl.hu.bep3.groep2.menumanger.core.domain.exception.MealNotFoundException;
import nl.hu.bep3.groep2.menumanger.core.port.storage.IngredientRepository;
import nl.hu.bep3.groep2.menumanger.core.port.storage.MealRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record MenuQueryHandler(MealRepository repository, IngredientRepository ingredientRepository) {
	public Meal handle(GetMealByName getMealByName) {
		String name = getMealByName.name();
		return this.repository.findByName(name).orElseThrow(() ->
				new MealNotFoundException("No meal with the name " + name + " was found"));
	}
	public List<Meal> handle() {
		List<Meal> meals = repository.findAll();
		List<Meal> available = new ArrayList<>();
		for (Meal meal : meals) {
			boolean allAvailable = true;
			for (String ingredient : meal.getIngredients().keySet()) {
				int amount = meal.getIngredients().get(ingredient);
				if (!ingredientRepository.areIngredientsAvailable(ingredient, amount)) {
					allAvailable = false;
				}
			}
			if (allAvailable) available.add(meal);
		}
		return available;
	}
}
