package nl.hu.bep3.groep2.menumanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.Map;

public record CreateOrder(int table, Map<String, Integer> meals) {
	@JsonCreator
	public CreateOrder {

	}
}
