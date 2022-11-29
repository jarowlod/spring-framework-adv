package pl.training.orders.ports.input;

import pl.training.orders.ports.model.OrderPort;

public interface PlaceOrderUseCase {

    void place(OrderPort orderPort);

}
