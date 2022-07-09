package saffchen.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import saffchen.dto.ParticipantDto;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
@Service
public class ParticipantService {
    private final URL PARTICIPANTS = getClass().getClassLoader().getResource("participants.json");
    private final ObjectMapper om = new ObjectMapper();

    public List<ParticipantDto> getAll() {
        try {
            return om.readValue(PARTICIPANTS, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("Ошибка при чтении файла: {}", PARTICIPANTS);
            return java.util.Collections.emptyList();
        }
    }
}
