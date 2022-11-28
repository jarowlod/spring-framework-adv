package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.payments.domain.services.DefaultPaymentsFactory;
import pl.training.payments.ports.PaymentsFactory;
import pl.training.payments.ports.input.GetPaymentUseCase;
import pl.training.payments.ports.input.ProcessPaymentUseCase;
import pl.training.payments.ports.output.PaymentsReader;
import pl.training.payments.ports.output.PaymentsWriter;
import pl.training.payments.ports.output.TimeProvider;

@Configuration
public class PaymentConfiguration {

    private static final PaymentsFactory PAYMENTS_FACTORY = new DefaultPaymentsFactory(0.01);

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentsReader paymentsReader) {
        return PAYMENTS_FACTORY.getPaymentUseCase(paymentsReader);
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentsWriter paymentsWriter, TimeProvider timeProvider) {
        return PAYMENTS_FACTORY.processPaymentUseCase(paymentsWriter, timeProvider);
    }

}
