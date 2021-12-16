package nl.hu.bep3.groep2.menumanger.core.application;

import com.mongodb.DuplicateKeyException;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateMeal;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import nl.hu.bep3.groep2.menumanger.core.domain.exception.MealAlreadyExistsException;
import nl.hu.bep3.groep2.menumanger.core.port.storage.MealRepository;
import nl.hu.bep3.groep2.menumanger.core.domain.event.MealWasCreated;
import nl.hu.bep3.groep2.menumanger.core.port.messaging.Publisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MealCommandHandler {
	private final MealRepository repository;
	private final Publisher publisher;

	public MealCommandHandler(MealRepository repository, Publisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	public Meal handle(CreateMeal newMenu) {
		Meal meal = new Meal(newMenu.getName(),
				newMenu.getPrice(), newMenu.getIngredients());
		try {
			repository.save(meal);
		} catch (DuplicateKeyException e) {
			throw new MealAlreadyExistsException("Meal with name " + meal.getName() + " already exists");
		}
		publisher.publish(new MealWasCreated(meal));
		return meal;
	}
}
