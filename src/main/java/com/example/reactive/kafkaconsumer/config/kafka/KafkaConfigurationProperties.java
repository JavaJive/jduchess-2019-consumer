package com.example.reactive.kafkaconsumer.config.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.kafka")
public class KafkaConfigurationProperties {

  private String bootstrapServers;
}
