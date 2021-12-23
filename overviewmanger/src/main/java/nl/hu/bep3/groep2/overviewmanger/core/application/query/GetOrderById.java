package nl.hu.bep3.groep2.overviewmanger.core.application.query;

import lombok.Getter;

import java.util.UUID;

@Getter
public class GetOrderById {
    private final UUID id;

    public GetOrderById(UUID id) {
        this.id = id;
    }
}
