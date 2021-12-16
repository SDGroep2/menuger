package nl.hu.bep3.groep2.overviewmanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Getter
@Setter
public class Meal {
    @Id
    private String name;
    private List<String> ingredients;
    private double price;

    public Meal(String name, List<String> ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }
}
