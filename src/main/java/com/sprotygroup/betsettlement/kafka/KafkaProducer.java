package com.sprotygroup.betsettlement.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.requireNonNull;

@Slf4j
@Component
public class KafkaProducer<V> {

    private final KafkaTemplate<String, V> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, V> kafkaTemplate) {
        this.kafkaTemplate = requireNonNull(kafkaTemplate, "kafkaTemplate");
    }

    /**
     * Sends a message to the given Kafka topic.
     *
     * @param topic topic name
     * @param key   String key (fixed)
     * @param value generic payload
     */
    public CompletableFuture<Void> send(String topic, String key, V value) {
        CompletableFuture<SendResult<String, V>> future;
        if (key != null) {
            future = kafkaTemplate.send(topic, key, value);
        } else {
            future = kafkaTemplate.send(topic, value);
        }

        return future.toCompletableFuture().thenAccept(result -> {
            RecordMetadata metadata = result.getRecordMetadata();
//            log.info("Message send: topic = {}, partition = {}, offset = {}",
//                    metadata.topic(), metadata.partition(), metadata.offset());
        });
    }

}
