package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.config.KafkaProducerConfig;
import saffchen.config.KafkaTopicConfig;
import saffchen.kafka.StockMessage;

@Service
public class KafkaProducerService {
    private final KafkaProducerConfig kafkaProducerConfig;

    private final KafkaTopicConfig kafkaTopicConfig;

    @Autowired
    public KafkaProducerService(KafkaProducerConfig kafkaProducerConfig, KafkaTopicConfig kafkaTopicConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
        this.kafkaTopicConfig = kafkaTopicConfig;
    }

    public String sendMessage(StockMessage message) {
        try {
            kafkaProducerConfig.kafkaTemplate().send(message.getTopic(), message.getMessage());
        } catch (Exception e) {
            return "Topic not found!";
        }
        return "Message sent!";
    }

    public String sendMessageForReport(StockMessage message){
        try{
            //TODO
        } catch (Exception e) {
            return "Topic not found!";
        }

        return null;
    }

}
