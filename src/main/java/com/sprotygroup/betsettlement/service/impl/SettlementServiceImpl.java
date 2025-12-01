package com.sprotygroup.betsettlement.service.impl;

import com.sprotygroup.betsettlement.dto.BetSettlementMessageDTO;
import com.sprotygroup.betsettlement.dto.EventOutcomeDTO;
import com.sprotygroup.betsettlement.model.Bet;
import com.sprotygroup.betsettlement.repository.BetRepository;
import com.sprotygroup.betsettlement.rocketmq.RocketMQProducer;
import com.sprotygroup.betsettlement.service.SettlementService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class SettlementServiceImpl implements SettlementService {

    private static final Logger log = LoggerFactory.getLogger(SettlementServiceImpl.class);

    private final BetRepository betRepository;
    private final RocketMQProducer rocketMQProducer;
    private final String betSettlementTopic;

    public SettlementServiceImpl(BetRepository betRepository,
                                 RocketMQProducer rocketMQProducer,
                                 @Value("${rocketmq.betsettlement.topic}") String betSettlementTopic) {
        this.betRepository = requireNonNull(betRepository, "betRepository");
        this.rocketMQProducer = requireNonNull(rocketMQProducer, "rocketMQProducer");
        this.betSettlementTopic = requireNonNull(betSettlementTopic, "betSettlementTopic");
    }

    @Override
    public void processBet(EventOutcomeDTO eventOutcomeDTO) {
        log.info("Processing event outcome for event id = {}", eventOutcomeDTO.getEventId());

        List<Bet> bets = betRepository.findByEventId(eventOutcomeDTO.getEventId());

        if (bets.isEmpty()) {
            log.info("No bets found for given event id = {}", eventOutcomeDTO.getEventId());
            return;
        }

        List<Bet> betsToSettle = bets.stream()
                .filter(bet -> !bet.isSettled())
                .toList();

        if (betsToSettle.isEmpty()) {
            log.info("All bets already settled for event {}", eventOutcomeDTO.getEventId());
            return;
        }

        for (Bet bet : betsToSettle) {
            BetSettlementMessageDTO dto = new BetSettlementMessageDTO.Builder()
                    .betId(bet.getId())
                    .userId(bet.getUserId())
                    .eventId(bet.getEventId())
                    .eventMarketId(bet.getEventMarketId())
                    .winnerId(eventOutcomeDTO.getEventWinnerId())
                    .isWinningBet(eventOutcomeDTO.getEventWinnerId().equals(bet.getUserId()))
                    .build();

            rocketMQProducer.send(betSettlementTopic, dto);
        }
    }

    @Override
    @Transactional
    public void settleBet(BetSettlementMessageDTO betSettlementMessageDTO) {
        try {
            Optional<Bet> optionalBet = betRepository.findById(betSettlementMessageDTO.getBetId());

            if (optionalBet.isPresent()) {
                Bet bet = optionalBet.get();
                bet.setWinnerId(betSettlementMessageDTO.getWinnerId());
                bet.setWon(betSettlementMessageDTO.isWinningBet());
                bet.setSettled(true);

                betRepository.save(bet);
                log.info("Bet {} has been settled successfully", betSettlementMessageDTO.getBetId());
            } else {
                log.info("Bet {} not found in the database", betSettlementMessageDTO.getBetId());
            }
        } catch (Exception e) {
            log.error("Exception settling the bet {}", betSettlementMessageDTO.getBetId());
            log.error(e.getMessage());
            // Not throwing the error to parent method for now.
        }
    }


}
