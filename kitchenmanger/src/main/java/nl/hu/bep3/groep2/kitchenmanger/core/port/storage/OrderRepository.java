package nl.hu.bep3.groep2.kitchenmanger.core.port.storage;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {
}
