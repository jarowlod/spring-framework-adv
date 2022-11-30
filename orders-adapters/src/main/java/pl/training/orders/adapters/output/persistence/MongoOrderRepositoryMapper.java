package pl.training.orders.adapters.output.persistence;

import org.mapstruct.Mapper;
import pl.training.orders.ports.model.OrderEntryPort;
import pl.training.orders.ports.model.OrderIdPort;
import pl.training.orders.ports.model.OrderPort;
import pl.training.orders.ports.model.PaymentPort;

@Mapper(componentModel = "spring")
public interface MongoOrderRepositoryMapper {

    OrderEntryDocument toDocument(OrderEntryPort orderEntryPort);

    OrderEntryPort toPort(OrderEntryDocument orderEntryDocument);

    OrderIdPort toPort(String idDocument);

    default OrderDocument toDocument(OrderPort orderPort, PaymentPort paymentPort) {
        var orderDocument = new OrderDocument();
        orderDocument.setEntries(orderPort.getEntries().stream().map(this::toDocument).toList());
        orderDocument.setPaymentStatus(paymentPort.getStatus());
        return orderDocument;
    }

    default String toDocument(OrderIdPort orderIdPort) {
        return orderIdPort.getValue();
    }

    default OrderPort toPort(OrderDocument orderDocument) {
        var idPort = toPort(orderDocument.getId());
        var entriesPort = orderDocument.getEntries().stream().map(this::toPort).toList();
        return new OrderPort(idPort, entriesPort);
    }

}
