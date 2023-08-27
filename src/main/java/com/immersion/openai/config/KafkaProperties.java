package com.immersion.openai.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka")
@Data
public class KafkaProperties {
    public static final String CONSUMER_GROUP_ID = "chatGpt";

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootStrapServers;
}
