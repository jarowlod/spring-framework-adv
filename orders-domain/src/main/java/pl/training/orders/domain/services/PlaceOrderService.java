package pl.training.orders.domain.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import pl.training.orders.ports.input.PlaceOrderUseCase;
import pl.training.orders.ports.model.OrderPort;
import pl.training.orders.ports.output.OrdersWriter;
import pl.training.orders.ports.output.PaymentService;

import static java.util.Collections.emptyMap;
import static pl.training.orders.ports.output.PaymentService.DEFAULT_PAYMENT_CURRENCY;

@Log
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

    private final PaymentService paymentService;
    private final OrdersWriter ordersWriter;

    @Override
    public void place(OrderPort orderPort) {
        var totalValue = orderPort.getTotalValue();
        log.info("Processing new Order with value %s %s".formatted(totalValue, DEFAULT_PAYMENT_CURRENCY));
        var paymentPort = paymentService.pay(totalValue, DEFAULT_PAYMENT_CURRENCY, emptyMap())
                .orElseThrow(IllegalStateException::new);
        log.info("Payment processed " + paymentPort);
        ordersWriter.persist(orderPort, paymentPort);
    }

}
