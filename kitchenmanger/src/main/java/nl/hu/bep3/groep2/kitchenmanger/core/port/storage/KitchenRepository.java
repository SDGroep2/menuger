package nl.hu.bep3.groep2.kitchenmanger.core.port.storage;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Kitchen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface KitchenRepository extends MongoRepository<Kitchen, UUID> {
	List<Kitchen> findByName(String name);
}
