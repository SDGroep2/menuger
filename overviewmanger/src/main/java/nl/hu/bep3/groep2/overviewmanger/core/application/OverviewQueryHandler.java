package nl.hu.bep3.groep2.overviewmanger.core.application;

import nl.hu.bep3.groep2.overviewmanger.core.application.query.GetOrderById;
import nl.hu.bep3.groep2.overviewmanger.core.domain.Order;
import nl.hu.bep3.groep2.overviewmanger.core.domain.OrderInfo;
import nl.hu.bep3.groep2.overviewmanger.core.domain.exception.OrderNotFoundException;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.KitchenRepository;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OverviewQueryHandler {
    private final OrderRepository orderRepository;

    public OverviewQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderInfo> handle() {
        List<OrderInfo> orders = new ArrayList<>();
        orders = orderRepository.findAll();
        return orders;
    }
}
