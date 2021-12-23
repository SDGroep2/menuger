package nl.hu.bep3.groep2.overviewmanger.core.application;

import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.overviewmanger.core.domain.Meal;
import nl.hu.bep3.groep2.overviewmanger.core.domain.Order;
import nl.hu.bep3.groep2.overviewmanger.core.domain.OrderInfo;
import nl.hu.bep3.groep2.overviewmanger.core.domain.Status;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.KitchenRepository;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.OrderRepository;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.OverviewRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OverviewCommandHandler {
    private final OverviewRepository overviewRepository;
    private final KitchenRepository kitchenRepository;
    private final OrderRepository orderRepository;

    public OverviewCommandHandler(OverviewRepository overviewRepository, KitchenRepository kitchenRepository, OrderRepository orderRepository) {
        this.overviewRepository = overviewRepository;
        this.kitchenRepository = kitchenRepository;
        this.orderRepository = orderRepository;
    }

    public void handle(OrderCreated orderCreated) {
        Order order = kitchenRepository.findById(orderCreated.getId());
        int amountOrdered = 0;
        for (String meal : order.getMeals().keySet()) {
            Optional<Meal> dbMeal = overviewRepository.findByName(meal);
            if (dbMeal.isPresent()) {
                Meal actualMeal = dbMeal.get();
                actualMeal.setAmount(actualMeal.getAmount() + order.getMeals().get(meal));
                actualMeal.addOrderedAt(orderCreated.getCreatedAt());
                overviewRepository.save(actualMeal);
            }
            else {
                overviewRepository.save(new Meal(meal, order.getMeals().get(meal), List.of(orderCreated.getCreatedAt())));
            }
            amountOrdered += order.getMeals().get(meal);
        }
        OrderInfo orderInfo = new OrderInfo(orderCreated.getId(), orderCreated.getCreatedAt(), null, amountOrdered);
        orderRepository.save(orderInfo);
    }

    public void handle(OrderUpdated orderUpdated) {
        Optional<OrderInfo> orderInfo = this.orderRepository.findById(orderUpdated.getId());
        if (orderInfo.isPresent() && orderUpdated.getStatus().equals(Status.FINISHED)) {
            OrderInfo actualOrderInfo = orderInfo.get();
            actualOrderInfo.setFinishedAt(orderUpdated.getUpdatedAt());
            this.orderRepository.save(actualOrderInfo);
        }
    }
}