package nl.hu.bep3.groep2.inventorymanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "ingredients")
@Getter
@Setter
public class Ingredient {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String name;
    private int amount;
    private int amountReserved;

    public Ingredient(UUID id, String name, int amount, int amountReserved) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.amountReserved = amountReserved;
    }
}
