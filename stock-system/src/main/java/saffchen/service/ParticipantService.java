package saffchen.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import saffchen.dto.ParticipantDto;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParticipantService {
    private static final String PARTICIPANT_FILE = "classpath:participants.json";
    private final ObjectMapper om;

    public List<ParticipantDto> getAll() {
        try {
            return om.readValue(ResourceUtils.getFile(PARTICIPANT_FILE), new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("Ошибка при чтении файла: {}", PARTICIPANT_FILE);

            return emptyList();
        }
    }
}
