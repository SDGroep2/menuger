package nl.hu.bep3.groep2.menumanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public record CreateOrder(int table, List<String> meals) {
	@JsonCreator
	public CreateOrder {

	}
}
