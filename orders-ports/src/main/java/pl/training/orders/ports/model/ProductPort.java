package pl.training.orders.ports.model;

import lombok.Value;

@Value
public class ProductPort {

    Long id;
    String name;
    String price;

}
