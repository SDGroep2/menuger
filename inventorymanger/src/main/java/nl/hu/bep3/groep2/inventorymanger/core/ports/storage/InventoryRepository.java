package nl.hu.bep3.groep2.inventorymanger.core.ports.storage;

import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends MongoRepository<Ingredient, UUID> {
    Optional<Ingredient> findByName(String name);
}
