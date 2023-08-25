package com.immersion.openai;

import com.immersion.openai.config.KafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties(value = KafkaProperties.class)
@EnableFeignClients
public class GptApplication {

	public static void main(String[] args) {
		SpringApplication.run(GptApplication.class, args);
	}

}
