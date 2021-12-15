package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.InventoryRepository;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.messaging.Publisher;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driver.messaging.event.InventoryEvent;
import org.springframework.stereotype.Service;

@Service
public class InventoryCommandHandler {
    private final InventoryRepository repository;
    private final Publisher publisher;

    public InventoryCommandHandler(InventoryRepository repository, Publisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void handle(InventoryEvent inventoryEvent) {
        inventoryEvent.getIngredients().keySet().forEach((name)->{
            System.out.println(repository.findByName(name).orElseThrow(() -> new RuntimeException("doo doo brain")).getName());
        });
    }
}
