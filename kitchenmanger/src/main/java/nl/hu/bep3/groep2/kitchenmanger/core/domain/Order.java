package nl.hu.bep3.groep2.kitchenmanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.OrderEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "orders")
@Getter
@Setter
public class Order {
    @Id
    private UUID id;
    private int table;
    private Map<String, Integer> meals;
    private Status status;

    public Order(UUID id, int table, Map<String, Integer> meals, Status status) {
        this.id = id;
        this.table = table;
        this.meals = meals;
        this.status = status;
    }
}
