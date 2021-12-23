package nl.hu.bep3.groep2.overviewmanger.core.application;

import nl.hu.bep3.groep2.overviewmanger.core.application.query.GetOrderStatistics;
import nl.hu.bep3.groep2.overviewmanger.core.domain.OrderInfo;
import nl.hu.bep3.groep2.overviewmanger.core.ports.storage.OrderRepository;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.web.dto.OrderStatistics;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class OverviewQueryHandler {
    private final OrderRepository orderRepository;

    public OverviewQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderInfo> handle() {
        return orderRepository.findAll();
    }

    public OrderStatistics handle(GetOrderStatistics getOrderStatistics) {
        List<OrderInfo> orderInfos = orderRepository.findAll();
        Duration shortest = null;
        Duration longest = Duration.ZERO;
        Duration total = Duration.ZERO;
        int amountOfOrders = 0;
        int amountOfMeals = 0;
        for (OrderInfo orderInfo : orderInfos) {
            if (orderInfo.getFinishedAt() != null) {
                Duration completionTime = Duration.between(orderInfo.getCreatedAt(), orderInfo.getFinishedAt());
                if (shortest == null || completionTime.compareTo(shortest) < 0) {
                    shortest = completionTime;
                }
                longest = completionTime.compareTo(longest) > 0 ? completionTime : longest;
                total = total.plus(completionTime);
                amountOfMeals += orderInfo.getAmountOrdered();
                amountOfOrders++;
            }
        }
        Duration averageTimePerOrder = total.dividedBy(amountOfOrders);
        Duration averageTimePerMeal = total.dividedBy(amountOfMeals);
        return new OrderStatistics(shortest, longest, averageTimePerOrder, averageTimePerMeal);
    }
}
