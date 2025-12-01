package com.sprotygroup.betsettlement.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class RocketMQProducer {

    private static final Logger log = LoggerFactory.getLogger(RocketMQProducer.class);

    private final RocketMQTemplate rocketMQTemplate;

    public RocketMQProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = requireNonNull(rocketMQTemplate,"rocketMQTemplate");
    }

    public <T> void send(String topic, T payload) {
        rocketMQTemplate.convertAndSend(topic, payload);
        log.info("Send message to topic = {}", topic);
    }
}
