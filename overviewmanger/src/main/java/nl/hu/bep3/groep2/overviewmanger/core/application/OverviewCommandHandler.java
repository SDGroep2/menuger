package nl.hu.bep3.groep2.overviewmanger.core.application;

import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.KitchenRepository;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.OverviewRepository;
import org.springframework.stereotype.Service;

@Service
public class OverviewCommandHandler {
    private final OverviewRepository overviewRepository;
    private final KitchenRepository kitchenRepositoryRepository;

    public OverviewCommandHandler(OverviewRepository overviewRepository, KitchenRepository kitchenRepositoryRepository) {
        this.overviewRepository = overviewRepository;
        this.kitchenRepositoryRepository = kitchenRepositoryRepository;
    }

    public void handle(OrderCreated orderCreated) {
        System.out.println(orderCreated.getId());
        System.out.println(kitchenRepositoryRepository.findById(orderCreated.getId()));
    }

    public void handle(OrderUpdated orderUpdated) {
        System.out.println(orderUpdated.getId());
    }
}