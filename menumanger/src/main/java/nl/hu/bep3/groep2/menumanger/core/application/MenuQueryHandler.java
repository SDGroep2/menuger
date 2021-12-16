package nl.hu.bep3.groep2.menumanger.core.application;

import nl.hu.bep3.groep2.menumanger.core.application.query.GetMenusByName;
import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import nl.hu.bep3.groep2.menumanger.core.port.storage.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record MenuQueryHandler(MealRepository repository) {

	public List<Meal> handle(GetMenusByName getMenuByName) {
		String name = getMenuByName.getName();
		return this.repository.findAllByName(name);
	}
}
