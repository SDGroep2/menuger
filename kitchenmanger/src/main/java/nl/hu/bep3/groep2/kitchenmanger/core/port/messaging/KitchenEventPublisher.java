package nl.hu.bep3.groep2.kitchenmanger.core.port.messaging;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.KitchenEvent;

public interface KitchenEventPublisher {
    void publish(KitchenEvent event);
}
