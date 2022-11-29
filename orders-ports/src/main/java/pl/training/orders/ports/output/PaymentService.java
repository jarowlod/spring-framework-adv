package pl.training.orders.ports.output;

import pl.training.orders.ports.model.PaymentPort;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface PaymentService {

    String DEFAULT_PAYMENT_CURRENCY = "PLN";

    Optional<PaymentPort> pay(BigDecimal valuePort, String currencyPort, Map<String, String> propertiesPort);

}
