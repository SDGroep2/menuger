package nl.hu.bep3.groep2.menumanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateMeal {
	private final String name;
	private final double price;
	private final List<String> ingredients;

	@JsonCreator
	public CreateMeal(String name, double price, List<String> ingredients) {
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
	}
}
