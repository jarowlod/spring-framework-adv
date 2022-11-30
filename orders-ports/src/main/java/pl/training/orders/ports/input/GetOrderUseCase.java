package pl.training.orders.ports.input;

import pl.training.orders.ports.model.OrderIdPort;
import pl.training.orders.ports.model.OrderPort;

public interface GetOrderUseCase {

    OrderPort getById(OrderIdPort orderIdPort);

}
