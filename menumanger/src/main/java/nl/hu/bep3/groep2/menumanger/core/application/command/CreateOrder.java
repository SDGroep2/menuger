package nl.hu.bep3.groep2.menumanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record CreateOrder(UUID id, int table, Map<String, Integer> meals) {
	@JsonCreator
	public CreateOrder {

	}
}
