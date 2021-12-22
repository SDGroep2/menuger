package nl.hu.bep3.groep2.menumanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

public record CreateMeal(String name, double price,
                         Map<String, Integer> ingredients) {
	@JsonCreator
	public CreateMeal {
	}


}
