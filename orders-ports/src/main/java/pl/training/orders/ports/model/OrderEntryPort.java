package pl.training.orders.ports.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderEntryPort {

    Long productId;
    BigDecimal value;
    int quantity;

    public BigDecimal getTotalValue() {
        return value.multiply(BigDecimal.valueOf(quantity));
    }

}
