package pl.training.orders.adapters.output.persistence;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderEntryDocument {

    private Long productId;
    private BigDecimal value;
    private int quantity;

}
