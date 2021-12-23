package nl.hu.bep3.groep2.overviewmanger.infrastructure.config;

import nl.hu.bep3.groep2.overviewmanger.infrastructure.driven.storage.HttpKitchenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.kitchen}")
    private String kitchenRootPath;

    @Bean
    public HttpKitchenRepository httpMenuRepository() {
        return new HttpKitchenRepository(kitchenRootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
