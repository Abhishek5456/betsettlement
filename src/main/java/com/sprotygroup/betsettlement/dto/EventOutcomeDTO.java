package com.sprotygroup.betsettlement.dto;


public class EventOutcomeDTO {

    private String eventId;

    private String eventName;

    private Long eventWinnerId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getEventWinnerId() {
        return eventWinnerId;
    }

    public void setEventWinnerId(Long eventWinnerId) {
        this.eventWinnerId = eventWinnerId;
    }

    public EventOutcomeDTO() {
    }
}
