package nl.hu.bep3.groep2.overviewmanger.core.application;

import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.OverviewRepository;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driven.messaging.Publisher;
import org.springframework.stereotype.Service;

@Service
public class OverviewCommandHandler {
    private final OverviewRepository repository;
    private final Publisher publisher;

    public OverviewCommandHandler(OverviewRepository repository, Publisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void handle(OrderCreated orderCreated) {
        System.out.println(orderCreated.getId());
    }

    public void handle(OrderUpdated orderUpdated) {
        System.out.println(orderUpdated.getId());
    }
}
