package nl.hu.bep3.groep2.overviewmanger.infrastructure.driven.storage;

import nl.hu.bep3.groep2.overviewmanger.core.domain.Order;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HttpKitchenRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpKitchenRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    public Order getMealsFromOrder(String order) {
        URI uri = URI.create(this.rootPath + "?name=" + order);
        return this.client.getForObject(uri, Order.class);
    }
}
