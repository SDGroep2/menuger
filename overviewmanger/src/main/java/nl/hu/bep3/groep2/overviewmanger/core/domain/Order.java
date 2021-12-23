package nl.hu.bep3.groep2.overviewmanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.UUID;

@Document
@Getter
@Setter
public class Order {
    @Id
    private UUID id;
    private Integer table;
    private Status status;
    private HashMap<String, Integer> meals;

    public Order(UUID id, Integer table, Status status, HashMap<String, Integer> meals) {
        this.id = id;
        this.table = table;
        this.status = status;
        this.meals = meals;
    }
}
