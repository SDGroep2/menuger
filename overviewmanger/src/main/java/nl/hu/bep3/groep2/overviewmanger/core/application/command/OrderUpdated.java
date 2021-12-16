package nl.hu.bep3.groep2.overviewmanger.core.application.command;

import lombok.Getter;
import nl.hu.bep3.groep2.overviewmanger.core.domain.Status;

import java.util.UUID;

@Getter
public class OrderUpdated {
    private UUID id;
    private String status;


    public OrderUpdated(UUID id, String status) {
        this.id = id;
        this.status = status;
    }
}
