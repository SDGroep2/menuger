package nl.hu.bep3.groep2.overviewmanger.core.ports.storage;

import nl.hu.bep3.groep2.overviewmanger.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OverviewRepository extends MongoRepository<Order, UUID> {

}

