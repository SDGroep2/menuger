package nl.hu.bep3.groep2.menumanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "meals")
public class Meal {
	@Id
	private String name;
	private double price;
	private List<String> ingredients;

	public Meal(String name, double price, List<String> ingredients) {
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
	}
}
