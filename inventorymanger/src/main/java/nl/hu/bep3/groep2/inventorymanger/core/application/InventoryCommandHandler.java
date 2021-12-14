package nl.hu.bep3.groep2.inventorymanger.core.application;

import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.InventoryRepository;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Component;

@Component
public class InventoryCommandHandler {
    private final InventoryRepository repository;
    private final Publisher publisher;

    public InventoryCommandHandler(InventoryRepository repository, Publisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }


}
