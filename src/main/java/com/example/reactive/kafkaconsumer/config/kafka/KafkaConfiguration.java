package com.example.reactive.kafkaconsumer.config.kafka;

import static java.util.Collections.singleton;

import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

@Configuration
@EnableConfigurationProperties(KafkaConfigurationProperties.class)
public class KafkaConfiguration {

  private final KafkaConfigurationProperties properties;

  public KafkaConfiguration(KafkaConfigurationProperties properties) {
    this.properties = properties;
  }

  @Bean
  KafkaReceiver<String, String> kafkaReceiver(KafkaConfigurationProperties properties) {
    ReceiverOptions<String, String> options = ReceiverOptions.create(
        Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers(),
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.GROUP_ID_CONFIG, "test-1"
        )
    );
    return KafkaReceiver.create(options.subscription(singleton("daily-quotes")));
  }
}
