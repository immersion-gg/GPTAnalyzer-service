package com.immersion.openai.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GptFeignConfiguration {

    private final String apiKey;

    public GptFeignConfiguration(@Value("${gpt.api.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    @Bean
    RequestInterceptor gptRequestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization","Bearer "+ apiKey);
    }
}
