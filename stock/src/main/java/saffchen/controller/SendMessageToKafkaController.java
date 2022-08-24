package saffchen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.kafka.StockMessage;
import saffchen.service.KafkaProducerService;


@RestController
@RequestMapping(value = SendMessageToKafkaController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SendMessageToKafkaController {

    static final String REST_URL = "/api/v1/message";

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public SendMessageToKafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody StockMessage message) throws JsonProcessingException {
        return ResponseEntity.ok(kafkaProducerService.sendMessage(message));
    }

}
