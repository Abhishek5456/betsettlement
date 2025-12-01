package com.sprotygroup.betsettlement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bets")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(name = "event_market_id", nullable = false)
    private String eventMarketId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "settled", nullable = false)
    private boolean settled = false;

    @Column(name = "won")
    private boolean won = false;

    @Column(name = "winner_id", nullable = true)
    private Long winnerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventMarketId() {
        return eventMarketId;
    }

    public void setEventMarketId(String eventMarketId) {
        this.eventMarketId = eventMarketId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
}
