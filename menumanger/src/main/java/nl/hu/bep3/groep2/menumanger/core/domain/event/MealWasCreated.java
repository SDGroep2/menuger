package nl.hu.bep3.groep2.menumanger.core.domain.event;

import lombok.Getter;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;

@Getter
public class MealWasCreated extends MealRelatedEvent {
	private final String eventKey = "menu.meals.created";

	public MealWasCreated(Meal meal) {
		super(meal);
	}
}
