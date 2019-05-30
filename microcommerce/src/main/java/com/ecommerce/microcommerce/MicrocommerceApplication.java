package com.ecommerce.microcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @SpringBootApplication 
 * Composante de ces 3 annotations
 * @Configuration
 * @EnableAutoConfiguration
 * @ComponentScan
 */

@SpringBootApplication 	// Activer l'auto configuration
@EnableSwagger2 		// Générer la documentation :
						// - http://localhost:9090/v2/api-docs 		=> format JSON
						// - http://localhost:9090/swagger-ui.html 	=> format HTML
public class MicrocommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrocommerceApplication.class, args);
	}

}
