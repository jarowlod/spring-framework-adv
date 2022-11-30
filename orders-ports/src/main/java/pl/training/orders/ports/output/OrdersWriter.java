package pl.training.orders.ports.output;

import pl.training.orders.ports.model.OrderPort;
import pl.training.orders.ports.model.PaymentPort;

public interface OrdersWriter {

    void persist(OrderPort  orderPort, PaymentPort paymentPort);

}
