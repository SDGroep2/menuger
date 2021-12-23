package nl.hu.bep3.groep2.inventorymanger.infrastructure.config;

import nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.storage.HttpKitchenRepository;
import nl.hu.bep3.groep2.inventorymanger.infrastructure.driven.storage.HttpMenuRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.menu}")
    private String menuRootPath;

    @Value("${http-client.root-path.kitchen}")
    private String kitchenRootPath;

    @Bean
    public HttpMenuRepository httpIngredientRepository() {
        return new HttpMenuRepository(menuRootPath, restTemplate());
    }

    @Bean
    public HttpKitchenRepository httpKitchenRepository() {
        return new HttpKitchenRepository(kitchenRootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
