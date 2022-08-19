package saffchen.kafka;

import lombok.Data;
import lombok.Value;

@Data
public class StockMessage {
    private String topic;
    private String message;
}
