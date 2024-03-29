package com.bootcamp.bank.creditos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean(name = "clientClientes")
    public WebClient webClientClientes() {
        return WebClient.create("http://localhost:8081");
    }

    @Bean(name = "clientCuentas")
    public WebClient webClientCuentas() {
        return WebClient.create("http://localhost:8083");
    }

    @Bean(name = "clientCreditos")
    public WebClient webClientCreditos() {
        return WebClient.create("http://localhost:8082");
    }
}
