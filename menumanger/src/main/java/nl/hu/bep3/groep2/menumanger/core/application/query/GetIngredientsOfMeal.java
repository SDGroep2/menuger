package nl.hu.bep3.groep2.menumanger.core.application.query;

import lombok.Getter;

@Getter
public class GetIngredientsOfMeal {
	String name;

	public GetIngredientsOfMeal(String name) {
		this.name = name;
	}
}
