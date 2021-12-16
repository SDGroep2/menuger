package nl.hu.bep3.groep2.menumanger.core.domain.event;

import lombok.Getter;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class MealRelatedEvent {
	private final UUID eventId = UUID.randomUUID();
	private final Instant eventDate = Instant.now();
	private final String eventKey = "";
	private final Meal meal;

	public MealRelatedEvent(Meal meal) {
		this.meal = meal;
	}
}
