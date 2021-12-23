package nl.hu.bep3.groep2.menumanger.infrastructure.driven.storage;

import nl.hu.bep3.groep2.menumanger.core.port.storage.IngredientRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HttpIngredientRepository implements IngredientRepository {
	private final String rootPath;
	private final RestTemplate client;

	public HttpIngredientRepository(String rootPath, RestTemplate client) {
		this.rootPath = rootPath;
		this.client = client;
	}

	@Override
	public boolean areIngredientsAvailable(String ingredient, int amount) {
		URI uri = URI.create(this.rootPath + "?name=" + ingredient.replaceAll(" ", "%20") + "&amount=" + amount);
		return Boolean.TRUE.equals(this.client.getForObject(uri, boolean.class));
	}
}
