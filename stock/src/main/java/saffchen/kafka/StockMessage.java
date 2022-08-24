package saffchen.kafka;

import lombok.Data;

@Data
public class StockMessage {
    private String topic;
    private String message;
}
