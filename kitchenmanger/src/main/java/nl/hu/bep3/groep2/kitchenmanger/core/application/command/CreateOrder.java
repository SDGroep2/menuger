package nl.hu.bep3.groep2.kitchenmanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Map;

public record CreateOrder(int table, Map<String, Integer> meals) {
    @JsonCreator
    public CreateOrder {
    }
}
