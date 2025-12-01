package com.sprotygroup.betsettlement.config.kafka;

import com.sprotygroup.betsettlement.dto.EventOutcomeDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventOutcomeDTO> eventOutcomeListenerFactory() {
        return kafkaListenerContainerFactory(EventOutcomeDTO.class);
    }

    private <V> ConsumerFactory<String, V> consumerFactory(Class<V> valueType) {
        JsonDeserializer<V> deserializer = new JsonDeserializer(valueType);
        deserializer.addTrustedPackages("com.sportygroup.betsettlement.dto.*");

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    private <V> ConcurrentKafkaListenerContainerFactory<String, V> kafkaListenerContainerFactory(Class<V> valueType) {
        ConcurrentKafkaListenerContainerFactory<String, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(valueType));
        return factory;
    }
}
