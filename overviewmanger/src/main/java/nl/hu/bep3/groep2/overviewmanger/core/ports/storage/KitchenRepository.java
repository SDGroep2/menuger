package nl.hu.bep3.groep2.overviewmanger.core.ports.storage;

import nl.hu.bep3.groep2.overviewmanger.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface KitchenRepository {
    Order findById(UUID id);
}
