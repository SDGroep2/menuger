package nl.hu.bep3.groep2.overviewmanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document
@Getter
@Setter
public class Meal {
    @Id
    private String name;
    private int amount;
    private List<Instant> orderedAt = new ArrayList<>();

    public Meal(String name, int amount, List<Instant> orderedAt) {
        this.name = name;
        this.amount = amount;
        this.orderedAt = orderedAt;
    }

    public void addOrderedAt(Instant createdAt) {
        orderedAt.add(createdAt);
    }
}

