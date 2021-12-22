package nl.hu.bep3.groep2.menumanger.core.application;

import nl.hu.bep3.groep2.menumanger.core.application.command.CreateMeal;
import nl.hu.bep3.groep2.menumanger.core.application.command.CreateOrder;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import nl.hu.bep3.groep2.menumanger.core.domain.event.MealWasCreated;
import nl.hu.bep3.groep2.menumanger.core.domain.exception.MealAlreadyExistsException;
import nl.hu.bep3.groep2.menumanger.core.domain.exception.MealNotFoundException;
import nl.hu.bep3.groep2.menumanger.core.port.messaging.Publisher;
import nl.hu.bep3.groep2.menumanger.core.port.storage.KitchenRepository;
import nl.hu.bep3.groep2.menumanger.core.port.storage.MealRepository;
import org.springframework.stereotype.Service;

@Service
public class MealCommandHandler {
	private final MealRepository repository;
	private final KitchenRepository kitchenRepository;
	private final Publisher publisher;

	public MealCommandHandler(MealRepository repository, KitchenRepository kitchenRepository, Publisher publisher) {
		this.repository = repository;
		this.kitchenRepository = kitchenRepository;
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
		createOrder.meals().forEach(meal -> repository.findByName(meal)
				.orElseThrow(() -> new MealNotFoundException(meal)));
		return kitchenRepository.save(createOrder);
	}
}
