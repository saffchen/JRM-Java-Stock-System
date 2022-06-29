package saffchen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saffchen.dto.ParticipantDto;
import saffchen.service.ParticipantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @GetMapping
    public List<ParticipantDto> getParticipants() {
        return participantService.getAll();
    }
}
