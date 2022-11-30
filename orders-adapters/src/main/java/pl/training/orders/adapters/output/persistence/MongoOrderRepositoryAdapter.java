package pl.training.orders.adapters.output.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.training.orders.ports.model.OrderIdPort;
import pl.training.orders.ports.model.OrderPort;
import pl.training.orders.ports.model.PaymentPort;
import pl.training.orders.ports.output.OrderReader;
import pl.training.orders.ports.output.OrdersWriter;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log
public class MongoOrderRepositoryAdapter implements OrdersWriter, OrderReader {

    private final MongoOrderRepository orderRepository;
    private final MongoOrderRepositoryMapper mapper;

    @Override
    public void persist(OrderPort orderPort, PaymentPort paymentPort) {
        var orderDocument = mapper.toDocument(orderPort, paymentPort);
        var result= orderRepository.save(orderDocument);
        log.info("Order saved: " + result);
    }

    @Override
    public Optional<OrderPort> getById(OrderIdPort orderIdPort) {
        var idDocument = mapper.toDocument(orderIdPort);
        return orderRepository.findById(idDocument)
                .map(mapper::toPort);
    }

}
