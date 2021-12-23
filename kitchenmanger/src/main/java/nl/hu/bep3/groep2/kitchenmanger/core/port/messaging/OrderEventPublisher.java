package nl.hu.bep3.groep2.kitchenmanger.core.port.messaging;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}
