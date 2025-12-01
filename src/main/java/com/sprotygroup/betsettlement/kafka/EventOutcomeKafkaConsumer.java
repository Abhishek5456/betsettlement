package com.sprotygroup.betsettlement.kafka;


import com.sprotygroup.betsettlement.dto.EventOutcomeDTO;
import com.sprotygroup.betsettlement.rocketmq.BetSettlementRocketMQConsumer;
import com.sprotygroup.betsettlement.service.SettlementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
public class EventOutcomeKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventOutcomeKafkaConsumer.class);
    private final SettlementService settlementService;

    public EventOutcomeKafkaConsumer(SettlementService settlementService) {
        this.settlementService = requireNonNull(settlementService, "settlementService");
    }

    @KafkaListener( topics = "${spring.kafka.eventoutcome.topic}",
                    groupId = "${spring.kafka.eventoutcome.groupId}",
                    containerFactory = "eventOutcomeListenerFactory")
    public void listen(EventOutcomeDTO eventOutcomeDTO) {

        settlementService.processBet(eventOutcomeDTO);
    }
}
