package nl.hu.bep3.groep2.kitchenmanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.OrderEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Document(collection = "orders")
@Getter
@Setter
public class Order {
    @Id
    private UUID id;
    private int table;
    private HashMap<String, Integer> meals;
    private Status status;
    @Transient
    private List<OrderEvent> events = new ArrayList<>();

    public Order(UUID id, int table, HashMap<String, Integer> meals, Status status) {
        this.id = id;
        this.table = table;
        this.meals = meals;
        this.status = status;
    }

    public void changeOrderStatus(Status status) {
        this.status = status;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public List<OrderEvent> listEvents() {
        return events;
    }
}
