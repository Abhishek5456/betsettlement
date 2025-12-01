package com.sprotygroup.betsettlement.repository;

import com.sprotygroup.betsettlement.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByEventId(String eventId);
}
