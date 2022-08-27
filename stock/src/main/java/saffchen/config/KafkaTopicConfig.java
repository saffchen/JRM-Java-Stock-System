package saffchen.config;


import lombok.Getter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Getter
public class KafkaTopicConfig {

    @Value(value="${kafka.topic.replicas}")
    private String topicReplicas;

    @Value(value="${kafka.topic.partitions}")
    private String topicPartitions;

    @Bean
    @Qualifier("${kafka.topic.name1}")
    public NewTopic createKafkaTopic1(@Value("${kafka.topic.name1}") String topicName) {

        return TopicBuilder
                .name(topicName)
                .partitions(Integer.valueOf(this.topicPartitions))
                .replicas(Integer.valueOf(this.topicReplicas))
                .build();
    }
    @Bean
    @Qualifier("${kafka.topic.name2}")
    public NewTopic createKafkaTopic2(@Value("${kafka.topic.name2}") String topicName) {
        return TopicBuilder
                .name(topicName)
                .partitions(Integer.valueOf(this.topicPartitions))
                .replicas(Integer.valueOf(this.topicReplicas))
                .build();
    }

    @Bean
    @Qualifier("${kafka.topic.name3")
    public NewTopic createKafkaTopic3(@Value("${kafka.topic.name3}") String topicName) {
        return TopicBuilder
                .name(topicName)
                .partitions(Integer.valueOf(this.topicPartitions))
                .replicas(Integer.valueOf(this.topicReplicas))
                .build();
    }

}