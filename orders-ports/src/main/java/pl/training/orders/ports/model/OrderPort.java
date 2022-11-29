package pl.training.orders.ports.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
public class OrderPort {

    Long id;
    List<OrderEntryPort> entries;

    public BigDecimal getTotalValue() {
        return entries.stream()
                .map(OrderEntryPort::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
