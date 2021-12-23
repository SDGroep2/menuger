package nl.hu.bep3.groep2.overviewmanger.core.ports.storage;

import nl.hu.bep3.groep2.overviewmanger.core.domain.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OverviewRepository extends MongoRepository<Meal, String>{
    Optional<Meal> findByName(String name);

}

