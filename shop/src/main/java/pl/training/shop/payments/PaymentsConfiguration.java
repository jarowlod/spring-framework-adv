package pl.training.shop.payments;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.payments.adapters.commons.aop.ExecutionInterceptor;
import pl.training.payments.adapters.commons.aop.LoggingAspect;
import pl.training.payments.domain.services.DefaultPaymentsFactory;
import pl.training.payments.ports.PaymentsFactory;
import pl.training.payments.ports.input.GetPaymentUseCase;
import pl.training.payments.ports.input.ProcessPaymentUseCase;
import pl.training.payments.ports.output.PaymentsReader;
import pl.training.payments.ports.output.PaymentsWriter;
import pl.training.payments.ports.output.TimeProvider;

@Configuration
public class PaymentsConfiguration {

    private static final PaymentsFactory PAYMENTS_FACTORY = new DefaultPaymentsFactory(0.01);

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentsReader paymentsReader) {
        return PAYMENTS_FACTORY.getPaymentUseCase(paymentsReader);
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentsWriter paymentsWriter, TimeProvider timeProvider) {
        return PAYMENTS_FACTORY.processPaymentUseCase(paymentsWriter, timeProvider);
    }

    @Bean
    public Advisor paymentProcessingAdvisor() {
        var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("bean(processPaymentUseCase)");
        return new DefaultPointcutAdvisor(pointcut, new ExecutionInterceptor(new LoggingAspect()));
    }

}
