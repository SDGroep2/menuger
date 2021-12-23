package nl.hu.bep3.groep2.kitchenmanger.core.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;

import java.util.HashMap;
import java.util.UUID;

public record ChangeOrderStatus(UUID id, int table, HashMap<String, Integer> meals, Status status) {
    @JsonCreator
    public ChangeOrderStatus {
    }
}
