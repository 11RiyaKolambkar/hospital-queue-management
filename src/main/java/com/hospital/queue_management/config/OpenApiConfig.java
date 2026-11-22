package com.hospital.queue_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hospitalQueueOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hospital Queue Management System API")
                        .description("Production-ready RESTful APIs for managing hospital patients, doctors, appointments, and real-time token queues.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Hospital Administration Support")
                                .email("support@hospital-queue.local"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
