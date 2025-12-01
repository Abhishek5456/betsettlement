package com.sprotygroup.betsettlement.service.impl;

import com.sprotygroup.betsettlement.dto.EventOutcomeDTO;
import com.sprotygroup.betsettlement.kafka.KafkaProducer;
import com.sprotygroup.betsettlement.service.EventOutcomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
public class EventOutcomeServiceImpl implements EventOutcomeService {

    private static final Logger log = LoggerFactory.getLogger(EventOutcomeServiceImpl.class);

    private final KafkaProducer<EventOutcomeDTO> kafkaProducer;
    private final String topic;

    public EventOutcomeServiceImpl(KafkaProducer<EventOutcomeDTO> kafkaProducer,
                                   @Value("${spring.kafka.eventoutcome.topic}") String topic) {
        this.kafkaProducer = requireNonNull(kafkaProducer, "kafkaProducer");
        this.topic = requireNonNull(topic, "topic");
    }

    @Override
    public void publishEvent(EventOutcomeDTO eventOutcomeDTO) {
        kafkaProducer.send(topic, eventOutcomeDTO.getEventId(), eventOutcomeDTO);

        log.info("Event published to kafka successfully");
    }
}
