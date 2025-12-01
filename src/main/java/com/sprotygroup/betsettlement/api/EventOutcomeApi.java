package com.sprotygroup.betsettlement.api;


import com.sprotygroup.betsettlement.dto.EventOutcomeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/events")
public interface EventOutcomeApi {

    @PostMapping
    ResponseEntity<String> publishEventOutcome(@RequestBody EventOutcomeDTO request);
}
