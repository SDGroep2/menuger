package nl.hu.bep3.groep2.menumanger.infrastructure.config;

import nl.hu.bep3.groep2.menumanger.infrastructure.driven.storage.HttpIngredientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
	@Value("${http-client.root-path.inventory}")
	private String rootPath;

	@Bean
	public HttpIngredientRepository httpIngredientRepository() {
		return new HttpIngredientRepository(rootPath, restTemplate());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

