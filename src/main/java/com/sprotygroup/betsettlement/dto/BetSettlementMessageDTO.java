package com.sprotygroup.betsettlement.dto;

public class BetSettlementMessageDTO {
    private Long betId;
    private Long userId;
    private String eventId;
    private String eventMarketId;
    private long winnerId;
    private boolean isWinningBet;

    // Private constructor to enforce use of builder
    private BetSettlementMessageDTO(Builder builder) {
        this.betId = builder.betId;
        this.userId = builder.userId;
        this.eventId = builder.eventId;
        this.eventMarketId = builder.eventMarketId;
        this.winnerId = builder.winnerId;
        this.isWinningBet = builder.isWinningBet;
    }

    // Getters
    public Long getBetId() { return betId; }
    public Long getUserId() { return userId; }
    public String getEventId() { return eventId; }
    public String getEventMarketId() { return eventMarketId; }
    public long getWinnerId() { return winnerId; }
    public boolean isWinningBet() { return isWinningBet; }

    // Builder class
    public static class Builder {
        private Long betId;
        private Long userId;
        private String eventId;
        private String eventMarketId;
        private long winnerId;
        private boolean isWinningBet;

        public Builder betId(Long betId) {
            this.betId = betId;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder eventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder eventMarketId(String eventMarketId) {
            this.eventMarketId = eventMarketId;
            return this;
        }

        public Builder winnerId(long winnerId) {
            this.winnerId = winnerId;
            return this;
        }

        public Builder isWinningBet(boolean isWinningBet) {
            this.isWinningBet = isWinningBet;
            return this;
        }

        public BetSettlementMessageDTO build() {
            return new BetSettlementMessageDTO(this);
        }
    }
}

