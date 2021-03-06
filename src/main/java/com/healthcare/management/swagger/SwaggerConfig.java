package com.healthcare.management.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author srikanthreddy
 * 
 * This class handles generation of swagger using Docket 
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * This method returns Docket used for Swagger Doc generation
	 * @return Docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.healthcare.management")).build().apiInfo(apiMetaData());

	}

	/**
	 * This method handles custom API meta info
	 * @return ApiInfo
	 */
	private ApiInfo apiMetaData() {
		return new ApiInfoBuilder().title("HealthCare Enrolle Management API").description(
				"This API provides Add , Retrieve, Update and Delete features for Enrollees and Respective Dependents in HealthCare Management")
				.termsOfServiceUrl("https://developer.here.com/terms-and-conditions").version("1.0").build();
	}

}
