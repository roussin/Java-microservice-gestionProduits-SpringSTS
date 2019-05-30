package com.ecommerce.microcommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration	// permet de remplacer un fichier de configuration classique en XML 
				// et donne accès aux méthodes pour la personnalisation de Swagger,
				// grâce à la classe Docket qui gère toutes les configurations.
@EnableSwagger2
public class SwaggerConfig {
	
	/*
	 * - select() 							// permet d'initialiser une classe du nom de ApiSelectorBuilder
	 * - apis()								// filtre la documentation à exposer selon les contrôleurs
	 * - RequestHandlerSelectors 			// est un prédicat (introduit depuis java 8) qui permet
	 * 							 			// de retourner TRUE ou FALSE selon la condition utilisée
 * 							 				// nous avons utilisé any() qui retournera toujours TRUE
	 * - path()								// cette méthode donne accès à une autre façon de filtrer : 
	 * 										// selon l'URI des requêtes. Ainsi, vous pouvez, par exemple, 
	 * 										// demander à Swagger de ne documenter que les méthodes qui 
	 * 										// répondent à des requêtes commençant par "/public" 
	 * - PathSelectors.regex("/Produits.*) 	// permet de passer une expression régulière qui n'accepte que les URI commençant par /Produits. 
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ecommerce.microcommerce"))
				.paths(PathSelectors.regex("/Produits.*"))
				.build();
	}

}
