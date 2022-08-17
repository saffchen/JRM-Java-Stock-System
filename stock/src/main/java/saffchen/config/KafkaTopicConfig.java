package saffchen.config;


import lombok.Getter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Getter
public class KafkaTopicConfig {
    @Value(value="${kafka.topic.names}")
    private String topicName;

    @Value(value="${kafka.topic.replicas}")
    private String topicReplicas;

    @Value(value="${kafka.topic.partitions}")
    private String topicPartitions;

    @Bean
    public NewTopic createKafkaTopic() {
        return TopicBuilder
                .name("topic1")
                .partitions(Integer.valueOf(this.topicPartitions))
                .replicas(Integer.valueOf(this.topicReplicas))
                .build();
    }

}