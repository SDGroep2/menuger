package nl.hu.bep3.groep2.menumanger.core.port.storage;

import nl.hu.bep3.groep2.menumanger.core.domain.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface MenuRepository extends MongoRepository<Menu, UUID> {
	List<Menu> findAllByName(String name);
}
