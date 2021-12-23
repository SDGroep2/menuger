package nl.hu.bep3.groep2.overviewmanger.core.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;


@Getter
@Setter
public class Order {
    @Id
    private UUID id;
    private Integer table;
    private Status status;
    private Map<String, Integer> meals;


    public Order(UUID id, Integer table, Status status, Map<String, Integer> meals) {
        this.id = id;
        this.table = table;
        this.status = status;
        this.meals = meals;
    }

    public Order(UUID id, Integer table, Map<String, Integer> meals) {
        this.id = id;
        this.table = table;
        this.meals = meals;
        this.status = Status.ORDERED;
    }

    @JsonCreator
    public Order(){}
}
