package pl.training.orders.domain.services;

import lombok.RequiredArgsConstructor;
import pl.training.orders.ports.input.GetOrderUseCase;
import pl.training.orders.ports.model.OrderIdPort;
import pl.training.orders.ports.model.OrderNotFoundException;
import pl.training.orders.ports.model.OrderPort;
import pl.training.orders.ports.output.OrderReader;

@RequiredArgsConstructor
public class GetOrderService implements GetOrderUseCase {

    private final OrderReader orderReader;

    @Override
    public OrderPort getById(OrderIdPort orderIdPort) {
        return orderReader.getById(orderIdPort)
                .orElseThrow(OrderNotFoundException::new);
    }

}
