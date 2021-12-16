package nl.hu.bep3.groep2.overviewmanger.core.application;

import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.OverviewRepository;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driven.messaging.Publisher;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event.OverviewCreatedOrderEvent;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event.OverviewEvent;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event.OverviewUpdatedOrderEvent;
import org.springframework.stereotype.Service;

@Service
public class OverviewCommandHandler {
    private final OverviewRepository repository;
    private final Publisher publisher;

    public OverviewCommandHandler(OverviewRepository repository, Publisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void handle(OverviewCreatedOrderEvent overviewEvent) {

    }

    public void handle(OverviewUpdatedOrderEvent overviewUpdatedOrderEvent) {

    }
}
