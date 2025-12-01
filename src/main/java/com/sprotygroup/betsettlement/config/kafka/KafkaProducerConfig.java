package com.sprotygroup.betsettlement.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    /**
     * Kafka Broker Endpoints.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Kafka acknowledgement configuration.
     * It determines the level of durability guarantees for messages sent to kafka cluster.
     * It dictates how many broker must confirm the receipt of a message before the producer consider the send operation successful
     */
    @Value("${spring.kafka.producer.acknowledgement}")
    private String acknowledgement;

    /**
     * Kafka retries configuration.
     * This setting specifies the maximum number of times the producer will retry sending a message if a retriable error occurs
     */
    @Value("${spring.kafka.producer.retries}")
    private Integer retries;

    /**
     * Kafka producer idempotence
     * ensure that messages are written to kafka topic exactly once
     */
    @Value("${spring.kafka.producer.idempotence}")
    private boolean isIdempotence;

    @Bean
    public <V> KafkaTemplate<String, V> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    private Map<String, Object> baseConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Reliability
        config.put(ProducerConfig.ACKS_CONFIG, acknowledgement);
        config.put(ProducerConfig.RETRIES_CONFIG, retries);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, isIdempotence);

        return config;
    }

    // Generic ProducerFactory
    private <T> ProducerFactory<String, T> producerFactory() {
        JsonSerializer<T> serializer = new JsonSerializer<>();
        serializer.setAddTypeInfo(false);

        return new DefaultKafkaProducerFactory<>(baseConfig(), new StringSerializer(), serializer);
    }
}
