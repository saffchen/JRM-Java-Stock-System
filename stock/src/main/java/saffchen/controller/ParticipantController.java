package saffchen.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.ParticipantDto;
import saffchen.service.ParticipantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = ParticipantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ParticipantController {

    static final String REST_URL = "/api/v1/participants";
    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getParticipants() {
        log.info("get all participants");
        return ResponseEntity.ok(participantService.getAll());
    }
}
