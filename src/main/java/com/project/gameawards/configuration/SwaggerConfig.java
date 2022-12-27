package com.project.gameawards.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {
	
			@Bean
			public OpenAPI springGameAwardsOpenAPI() {
				return new OpenAPI()
						.info(new Info()
							.title("Project Game Awards")
							.description("Project Game Awards")
							.version("v0.0.1")
						.license(new License()
							.name("Delfina Vicente")
							.url("https://github.com/Delfina8/game_awards-backend"))
						.contact(new Contact()
							.name("Delfina Vicente")
							.url("https://github.com/Delfina8")
							.email("githubdelfina@gmail.com")))
						.externalDocs(new ExternalDocumentation()
							.description("Github")
							.url("https://github.com/Delfina8/game_awards-backend"));
		}
			
			@Bean
			public OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

				return openApi -> {
					openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

						ApiResponses apiResponses = operation.getResponses();

						apiResponses.addApiResponse("200", createApiResponse("Success!"));
						apiResponses.addApiResponse("201", createApiResponse("Persisted Object!"));
						apiResponses.addApiResponse("204", createApiResponse("Deleted Object!"));
						apiResponses.addApiResponse("400", createApiResponse("Request Error!"));
						apiResponses.addApiResponse("401", createApiResponse("Unauthorized Access!"));
						apiResponses.addApiResponse("404", createApiResponse("Object Not Found!"));
						apiResponses.addApiResponse("500", createApiResponse("Application Error!"));
					}));
				};
			}
			private ApiResponse createApiResponse(String message) {

				return new ApiResponse().description(message);
			}

}