package pl.training.orders.adapters.input.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.orders.ports.model.OrderIdPort;
import pl.training.orders.ports.model.OrderPort;

@Mapper(componentModel = "spring")
public interface RestOrderMapper {

    OrderPort toPort(OrderDto orderDto);

    OrderDto toDto(OrderPort orderPort);

    default OrderIdPort toPort(String idDto) {
        return new OrderIdPort(idDto);
    }

}
