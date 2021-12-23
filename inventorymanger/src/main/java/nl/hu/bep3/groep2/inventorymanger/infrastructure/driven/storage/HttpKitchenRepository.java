package nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.storage;

import nl.hu.bep3.groep2.inventorymanger.core.ports.storage.KitchenRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

public class HttpKitchenRepository implements KitchenRepository {
	private final String rootPath;
	private final RestTemplate client;

	public HttpKitchenRepository(String rootPath, RestTemplate client) {
		this.rootPath = rootPath;
		this.client = client;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getMealsForOrder(UUID id) {
		URI uri = URI.create(this.rootPath + "/" + id + "/meals");
		return (Map<String, Integer>) this.client.getForObject(uri, Map.class);
	}
}
