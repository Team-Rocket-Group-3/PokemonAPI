package com.teamRocket.PokemonApi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version Curso 2020-2021
 * @author: veronica
 */
@Configuration
public class PokemonAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Pokemon API")
                        .description("Pokemon Web Service API Rest")
                        .contact(new Contact()
                                .name("Verónica Pinos y Guillermo Suárez")
                                .email("ejemplo@ejemplo.com")
                                .url("https://ejemplo.com"))
                        .version("1.0"));
    }
}
