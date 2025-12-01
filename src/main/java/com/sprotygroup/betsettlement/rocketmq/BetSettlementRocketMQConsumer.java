package com.sprotygroup.betsettlement.rocketmq;

import com.sprotygroup.betsettlement.dto.BetSettlementMessageDTO;
import com.sprotygroup.betsettlement.service.SettlementService;
import com.sprotygroup.betsettlement.service.impl.SettlementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
@RocketMQMessageListener(
        topic = "${rocketmq.betsettlement.topic}",
        consumerGroup = "bet-settlement-consumer-group",
        nameServer = "${spring.rocketmq.named-server}"
)
public class BetSettlementRocketMQConsumer implements RocketMQListener<BetSettlementMessageDTO> {

    private static final Logger log = LoggerFactory.getLogger(BetSettlementRocketMQConsumer.class);

    private final SettlementService settlementService;

    public BetSettlementRocketMQConsumer(SettlementService settlementService) {
        this.settlementService = requireNonNull(settlementService, "settlementService");
    }

    @Override
    public void onMessage(BetSettlementMessageDTO message) {
        log.info("Processing bet for settlement");
        settlementService.settleBet(message);
    }
}
