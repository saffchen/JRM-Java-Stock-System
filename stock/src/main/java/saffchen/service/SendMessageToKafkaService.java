package saffchen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.kafka_entities.StockMessage;
import saffchen.kafka_entities.StockProducer;

@Service
public class SendMessageToKafkaService {

    private StockProducer stockProducer;

    @Autowired
    public SendMessageToKafkaService(StockProducer stockProducer) {
        this.stockProducer = stockProducer;
    }

    public String  sendMessage(StockMessage message) throws JsonProcessingException {
        return this.stockProducer.sendMessage(message);
    }
}
