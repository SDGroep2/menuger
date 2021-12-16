package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver;

import nl.hu.bep3.groep2.overviewmanger.core.application.OverviewCommandHandler;
import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderCreated;
import nl.hu.bep3.groep2.overviewmanger.core.application.command.OrderUpdated;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.messaging.event.OverviewOrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private final OverviewCommandHandler overviewCommandHandler;

    public MessageListener(OverviewCommandHandler overviewCommandHandler) {
        this.overviewCommandHandler = overviewCommandHandler;
    }

    @RabbitListener(queues = "#{'${overview.order.queue}'}")
    public void listen(OverviewOrderEvent overviewEvent) {
        switch (overviewEvent.getEventKey()) {
            case "kitchen.orders.created" -> overviewCommandHandler.handle(new OrderCreated(overviewEvent.getOrderId()));
            case "kitchen.orders.updated" -> overviewCommandHandler.handle(new OrderUpdated(overviewEvent.getOrderId(), overviewEvent.getStatus()));
        }
    }
}
