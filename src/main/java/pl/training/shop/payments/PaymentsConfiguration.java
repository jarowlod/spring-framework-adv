package pl.training.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.shop.payments.domain.*;
import pl.training.shop.payments.ports.output.PaymentRepository;
import pl.training.shop.payments.ports.input.PaymentService;
import pl.training.shop.payments.ports.output.TimeProvider;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentFeeCalculator percentagePaymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator,
                                         PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return new PaymentProcessor(paymentIdGenerator, paymentFeeCalculator, paymentRepository, timeProvider);
    }

}
