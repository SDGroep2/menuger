package nl.hu.bep3.groep2.menumanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class CreateNewMenu {
	private final String name;

	@JsonCreator
	public CreateNewMenu(String name) {
		this.name = name;
	}
}
