package SigueTuCarrera.Calificaciones.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${app.services.arancel.url:http://localhost:8006}")
    private String arancelServiceUrl;

    @Bean
    public WebClient arancelWebClient() {
        return WebClient.builder()
                .baseUrl(arancelServiceUrl)
                .build();
    }
}