package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.overviewmanger.core.application.OverviewQueryHandler;
import nl.hu.bep3.groep2.overviewmanger.core.application.query.GetOrderStatistics;
import nl.hu.bep3.groep2.overviewmanger.core.domain.OrderInfo;
import nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.web.dto.OrderStatistics;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OverviewController {
    private final OverviewQueryHandler overviewQueryHandler;

    public OverviewController(OverviewQueryHandler overviewQueryHandler) {
        this.overviewQueryHandler = overviewQueryHandler;
    }

    @GetMapping()
    public List<OrderInfo> getAllOrderInfo() {
        return this.overviewQueryHandler.handle();
    }

    @GetMapping("/stats")
    public OrderStatistics getOrderStatistics() {
        return this.overviewQueryHandler.handle(new GetOrderStatistics());
    }
}
