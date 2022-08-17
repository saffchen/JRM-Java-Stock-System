package saffchen.kafka;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class StockMessage {
    private Long id;
    private String massageBody;
}
