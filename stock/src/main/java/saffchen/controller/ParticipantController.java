package saffchen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.ParticipantDto;
import saffchen.service.ParticipantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/api/v1/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> getParticipants() {
        return ResponseEntity.ok(participantService.getAll());
    }
}
