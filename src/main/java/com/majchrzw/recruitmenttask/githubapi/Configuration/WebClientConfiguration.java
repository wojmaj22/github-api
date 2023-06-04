package com.majchrzw.recruitmenttask.githubapi.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
	
	@Bean
	public WebClient webClient(WebClient.Builder builder){
		return builder.baseUrl("https://api.github.com").build();
	}
}
