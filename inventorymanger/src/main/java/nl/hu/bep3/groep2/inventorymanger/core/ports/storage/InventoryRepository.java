package nl.hu.bep3.groep2.inventorymanger.core.ports.storage;

import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends MongoRepository<Ingredient, UUID> {
    Optional<Ingredient> findByName(String name);
}
