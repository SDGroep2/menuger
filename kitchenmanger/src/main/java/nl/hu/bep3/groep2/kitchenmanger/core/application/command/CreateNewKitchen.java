package nl.hu.bep3.groep2.kitchenmanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class CreateNewKitchen {
	private final String name;

	@JsonCreator
	public CreateNewKitchen(String name) {
		this.name = name;
	}
}
