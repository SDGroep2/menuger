package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.application.query.GetIngredientsByName;
import nl.hu.bep3.groep2.inventorymanger.core.domain.Ingredient;
import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.InventoryRepository;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Service;

@Service
public class InventoryQueryHandler {
    private final InventoryRepository repository;
    private final Publisher publisher;

    public InventoryQueryHandler(InventoryRepository repository, Publisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public Ingredient handle(GetIngredientsByName getIngredientsByName) {
        return repository.findByName(getIngredientsByName.getName()).orElseThrow(() -> new RuntimeException("Not Found"));
    }

}
