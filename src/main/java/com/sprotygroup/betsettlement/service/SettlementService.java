package com.sprotygroup.betsettlement.service;

import com.sprotygroup.betsettlement.dto.BetSettlementMessageDTO;
import com.sprotygroup.betsettlement.dto.EventOutcomeDTO;

public interface SettlementService {

    /**
     * Process the event coming from kafka topic.
     * @param eventOutcomeDTO the bet event coming from kafka
     */
    public void processBet(EventOutcomeDTO eventOutcomeDTO);

    /**
     * Update the bet status received on rocket mq topic.
     * @param betSettlementMessageDTO information related to bet.
     */
    public void settleBet(BetSettlementMessageDTO betSettlementMessageDTO);
}
