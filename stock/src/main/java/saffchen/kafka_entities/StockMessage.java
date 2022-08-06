package saffchen.kafka_entities;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class StockMessage {
    private Long id;
    private String massageBody;
}
