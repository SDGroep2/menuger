package nl.hu.bep3.groep2.menumanger.core.application;

import com.mongodb.DuplicateKeyException;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateMeal;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateOrder;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import nl.hu.bep3.groep2.menumanger.core.domain.exception.MealAlreadyExistsException;
import nl.hu.bep3.groep2.menumanger.core.port.storage.MealRepository;
import nl.hu.bep3.groep2.menumanger.core.domain.event.MealWasCreated;
import nl.hu.bep3.groep2.menumanger.core.port.messaging.Publisher;
import org.springframework.stereotype.Service;

@Service
public class MealCommandHandler {
	private final MealRepository repository;
	private final Publisher publisher;

	public MealCommandHandler(MealRepository repository, Publisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	public Meal handle(CreateMeal newMenu) {
		Meal meal = new Meal(newMenu.name(),
				newMenu.price(), newMenu.ingredients());
		if (repository.findByName(newMenu.name()).isPresent()) {
			throw new MealAlreadyExistsException("Meal with name " + meal.getName() + " already exists");
		}
		repository.save(meal);
		publisher.publish(new MealWasCreated(meal));
		return meal;
	}

	public CreateOrder handle(CreateOrder createOrder) {
		return createOrder;
	}
}
