package pl.training.orders.ports.output;

import pl.training.orders.ports.model.OrderIdPort;
import pl.training.orders.ports.model.OrderPort;

import java.util.Optional;

public interface OrderReader {

    Optional<OrderPort> getById(OrderIdPort orderIdPort);

}
