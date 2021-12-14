package nl.hu.bep3.groep2.kitchenmanger.core.domain.event;

import java.util.UUID;

public class KitchenFinishedOrder extends KitchenEvent {
    private final UUID kitchen;
    private final String order;

    public KitchenFinishedOrder(UUID kitchen, String order) {
        this.kitchen = kitchen;
        this.order = order;
    }

    @Override
    public String getEventKey() {
        return "kitchen.order.finished";
    }

    public UUID getKitchen() {
        return kitchen;
    }

    public String getOrder() {
        return order;
    }
}
