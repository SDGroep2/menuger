package nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.storage;

import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.MenuRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

public class HttpMenuRepository implements MenuRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpMenuRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public Map<String, Integer> getIngredientsOfMeal(String meal) {
        URI uri = URI.create(this.rootPath + "/" + meal.replaceAll(" ", "%20") + "/ingredients");
        return (Map<String, Integer>) this.client.getForObject(uri, Map.class);
    }
}
