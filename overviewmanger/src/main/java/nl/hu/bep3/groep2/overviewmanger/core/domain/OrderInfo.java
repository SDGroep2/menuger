package nl.hu.bep3.groep2.overviewmanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document("orderInfo")
@Getter
@Setter
public class OrderInfo {
    private UUID id;
    private Instant createdAt;
    private Instant finishedAt;
    private int amountOrdered;

    public OrderInfo(UUID id, Instant createdAt, Instant finishedAt, int amountOrdered) {
        this.id = id;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.amountOrdered = amountOrdered;
    }
}
