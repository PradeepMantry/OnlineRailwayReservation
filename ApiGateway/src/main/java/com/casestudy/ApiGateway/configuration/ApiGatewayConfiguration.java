package com.casestudy.ApiGateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayrouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route(p -> p.path("/auth/**")
						.uri("lb://USERLOGINMICROSERVICE"))
				.route(p -> p.path("/search/**")
						.uri("lb://TRAINDETAILSMICROSERVICE"))
				.route(p -> p.path("/booking/**")						
						.uri("lb://TRAINBOOKINGMICROSERVICE"))
				.route(p -> p.path("/admin/**")
						.uri("lb://ADMINOPERATIONSMICROSERVICE"))
				.build();
		
	}
}
