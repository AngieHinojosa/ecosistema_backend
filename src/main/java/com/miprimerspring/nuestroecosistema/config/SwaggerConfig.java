package com.miprimerspring.nuestroecosistema.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Crea el contacto
        Contact contact = new Contact();
        contact.setName("Angie Developer");
        contact.setEmail("angiehinojosaaguilar@gmail.com");
        contact.setUrl("https://tu-portafolio.com");

        // Crea la información general de la API
        Info info = new Info()
                .title("EcoMarket & BancoSimple API")
                .version("1.0")
                .description("Documentación de la API para EcoMarket y BancoSimple")
                .contact(contact);

        // Devuelve el objeto OpenAPI con la información
        return new OpenAPI().info(info);
    }
}
