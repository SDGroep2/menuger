package nl.hu.bep3.groep2.menumanger.core.port.storage;

import nl.hu.bep3.groep2.menumanger.core.domain.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface MealRepository extends MongoRepository<Meal, UUID> {
	List<Meal> findAllByName(String name);
}
