package nl.hu.bep3.groep2.inventorymanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
public class Ingredient {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String name;

    public Ingredient(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
