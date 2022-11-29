package pl.training.orders.adapters.input.rest;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private List<OrderEntryDto> entries;

}
