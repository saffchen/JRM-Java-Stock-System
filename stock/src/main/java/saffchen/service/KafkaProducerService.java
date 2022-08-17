package saffchen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saffchen.config.KafkaProducerConfig;
import saffchen.config.KafkaTopicConfig;

@Service
public class KafkaProducerService {
    private final KafkaProducerConfig kafkaProducerConfig;

    private final KafkaTopicConfig kafkaTopicConfig;

    @Autowired
    public KafkaProducerService(KafkaProducerConfig kafkaProducerConfig, KafkaTopicConfig kafkaTopicConfig) {
        this.kafkaProducerConfig = kafkaProducerConfig;
        this.kafkaTopicConfig = kafkaTopicConfig;
    }

    public String sendMessage(String message){
        kafkaProducerConfig.kafkaTemplate().send(kafkaTopicConfig.getTopicName(), message);
        return "Message sent!";
    }

    public String createTopic(){
        kafkaTopicConfig.createKafkaTopic();
        return "Topic created!";
    }
}
