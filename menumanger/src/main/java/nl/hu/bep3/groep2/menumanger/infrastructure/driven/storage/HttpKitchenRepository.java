package nl.hu.bep3.groep2.menumanger.infrastructure.driven.storage;

import nl.hu.bep3.groep2.menumanger.core.application.command.CreateOrder;
import nl.hu.bep3.groep2.menumanger.core.port.storage.KitchenRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HttpKitchenRepository implements KitchenRepository {
	private final String rootPath;
	private final RestTemplate client;

	public HttpKitchenRepository(String rootPath, RestTemplate client) {
		this.rootPath = rootPath;
		this.client = client;
	}

	@Override
	public CreateOrder save(CreateOrder createOrder) {
		URI uri = URI.create(this.rootPath);
		return this.client.postForObject(uri, createOrder, CreateOrder.class);
	}
}
