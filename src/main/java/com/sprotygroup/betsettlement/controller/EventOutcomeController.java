package com.sprotygroup.betsettlement.controller;

import com.sprotygroup.betsettlement.api.EventOutcomeApi;
import com.sprotygroup.betsettlement.dto.EventOutcomeDTO;
import com.sprotygroup.betsettlement.service.EventOutcomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;

@RestController
public class EventOutcomeController implements EventOutcomeApi {

    private final EventOutcomeService eventOutcomeService;

    public EventOutcomeController(EventOutcomeService eventOutcomeService) {
        this.eventOutcomeService = requireNonNull(eventOutcomeService, "eventOutcomeService");
    }

    @Override
    public ResponseEntity<String> publishEventOutcome(EventOutcomeDTO request) {
        eventOutcomeService.publishEvent(request);
        return ResponseEntity.ok("Event published successfully");
    }
}
