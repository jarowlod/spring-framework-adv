package pl.training.shop.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.orders.ports.model.PaymentPort;
import pl.training.orders.ports.output.PaymentService;
import pl.training.payments.ports.input.ProcessPaymentUseCase;
import pl.training.payments.ports.model.MoneyPort;
import pl.training.payments.ports.model.PaymentRequestIdPort;
import pl.training.payments.ports.model.PaymentRequestPort;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentServiceAdapter implements PaymentService {

    private final ProcessPaymentUseCase processPaymentUseCase;

    @Override
    public Optional<PaymentPort> pay(BigDecimal valuePort, String currencyPort, Map<String, String> propertiesPort) {
        var paymentRequestPort = new PaymentRequestPort(new PaymentRequestIdPort(1L), new MoneyPort(valuePort, currencyPort));
        var paymentPort = processPaymentUseCase.process(paymentRequestPort);
        return Optional.of(new PaymentPort(paymentPort.getId().getValue(), paymentPort.getStatus().name()));
    }

}
