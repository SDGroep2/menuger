package nl.hu.bep3.groep2.kitchenmanger.core.domain.event;

import lombok.Getter;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Order;

import java.util.Map;
import java.util.UUID;

@Getter
public class OrderWasCreated extends OrderEvent{
    private final String eventKey = "kitchen.orders.created";
    private final UUID id;
    private final Map<String, Integer> meals;

    public OrderWasCreated(Order order) {
        this.id = order.getId();
        this.meals = order.getMeals();
    }
}
