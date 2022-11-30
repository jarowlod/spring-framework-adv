package pl.training.shop.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.orders.domain.services.GetOrderService;
import pl.training.orders.domain.services.PlaceOrderService;
import pl.training.orders.ports.input.GetOrderUseCase;
import pl.training.orders.ports.input.PlaceOrderUseCase;
import pl.training.orders.ports.output.OrderReader;
import pl.training.orders.ports.output.OrdersWriter;
import pl.training.orders.ports.output.PaymentService;
import pl.training.payments.ports.output.PaymentsReader;

@Configuration
public class OrdersConfiguration {

    @Bean
    public PlaceOrderUseCase placeOrderUseCase(PaymentService paymentService, OrdersWriter ordersWriter) {
        return new PlaceOrderService(paymentService, ordersWriter);
    }

    @Bean
    public GetOrderUseCase getOrderUseCase(OrderReader orderReader) {
        return new GetOrderService(orderReader);
    }

}
