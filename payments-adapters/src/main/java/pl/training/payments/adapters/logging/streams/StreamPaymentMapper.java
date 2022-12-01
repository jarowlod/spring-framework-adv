package pl.training.payments.adapters.logging.streams;

import org.mapstruct.Mapper;
import pl.training.payments.ports.model.MoneyPort;
import pl.training.payments.ports.model.PaymentIdPort;
import pl.training.payments.ports.model.PaymentPort;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface StreamPaymentMapper {

    PaymentEvent toEvent(PaymentPort paymentPort);

    PaymentPort toPort(PaymentEvent paymentEvent);

    default MoneyPort valueToPort(String valueEvent) {
        var parts = valueEvent.split("\\s");
        return new MoneyPort(new BigDecimal(parts[0]), parts[1]);
    }

    default PaymentIdPort toPort(String valueId) {
        return new PaymentIdPort(valueId);
    }

    default String toEvent(MoneyPort moneyPort) {
        return moneyPort.getValue() + " " + moneyPort.getCurrencySymbol();
    }

    default String toEvent(PaymentIdPort paymentIdPort) {
        return paymentIdPort.getValue();
    }

}
