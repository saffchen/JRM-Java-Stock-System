package saffchen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saffchen.kafka_entities.StockMessage;
import saffchen.service.SendMessageToKafkaService;

@RestController
@RequestMapping(value = SendMessageToKafkaController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class SendMessageToKafkaController {

    static final String REST_URL = "/api/message";
    private KafkaProducer kafkaProducer;
    private SendMessageToKafkaService sendMessageToKafkaService;

    @Autowired
    public SendMessageToKafkaController(KafkaProducer kafkaProducer, SendMessageToKafkaService sendMessageToKafkaService) {
        this.kafkaProducer = kafkaProducer;
        this.sendMessageToKafkaService = sendMessageToKafkaService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody StockMessage message) throws JsonProcessingException {
        return ResponseEntity.ok(sendMessageToKafkaService.sendMessage(message));
    }

}
