package nl.hu.bep3.groep2.overviewmanger.infrastructure.driver.web;

import nl.hu.bep3.groep2.overviewmanger.core.application.OverviewQueryHandler;
import nl.hu.bep3.groep2.overviewmanger.core.domain.OrderInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overview")
public class OverviewController {
    private final OverviewQueryHandler overviewQueryHandler;

    public OverviewController(OverviewQueryHandler overviewQueryHandler) {
        this.overviewQueryHandler = overviewQueryHandler;
    }

    @GetMapping("/orders")
    public List<OrderInfo> getActiveOrders() {
        return this.overviewQueryHandler.handle();
    }
}
