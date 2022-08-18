package saffchen.service;

import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
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

    public String sendMessage(String topicName, String message){
        kafkaProducerConfig.kafkaTemplate().send(topicName, message);
        return "Message sent!";
    }

}
