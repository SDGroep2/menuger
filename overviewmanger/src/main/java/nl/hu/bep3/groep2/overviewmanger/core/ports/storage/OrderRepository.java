package nl.hu.bep3.groep2.overviewmanger.core.ports.storage;

import nl.hu.bep3.groep2.overviewmanger.core.domain.OrderInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<OrderInfo, UUID> {
}
