package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver;

import nl.hu.bep3.groep2.overviewmanger.core.application.OverviewCommandHandler;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event.OverviewCreatedOrderEvent;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event.OverviewEvent;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event.OverviewUpdatedOrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private final OverviewCommandHandler overviewCommandHandler;

    public MessageListener(OverviewCommandHandler overviewCommandHandler) {
        this.overviewCommandHandler = overviewCommandHandler;
    }

    @RabbitListener(queues = "#{'${overview.queue}'}")
    public void listen(OverviewEvent overviewEvent) {
        switch (overviewEvent.getEventKey()) {
            case "kitchen.orders.created" -> overviewCommandHandler.handle((OverviewCreatedOrderEvent) overviewEvent);
            case "kitchen.orders.updated" -> overviewCommandHandler.handle((OverviewUpdatedOrderEvent) overviewEvent);
        }
    }
}
