package pl.training.payments.adapters.logging;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import pl.training.payments.ports.model.PaymentPort;
import pl.training.payments.ports.model.PaymentRequestPort;

@Aspect
@Component
@Log
public class ConsolePaymentsProcessingLogger {

    @Before("bean(processPaymentUseCase) && args(paymentRequestPort)")
    public void onStart(JoinPoint joinPoint, PaymentRequestPort paymentRequestPort) {
        // var paymentRequestPort = (PaymentRequestPort) joinPoint.getArgs()[0];
        log.info("New payment request: " + paymentRequestPort);
    }

    @AfterReturning(value = "bean(processPaymentUseCase)", returning = "paymentPort")
    public void onSuccess(PaymentPort paymentPort) {
        log.info("A new payment of %s has been initialized".formatted(paymentPort.getValue()));
    }

    @AfterThrowing(value = "bean(processPaymentUseCase)", throwing = "runtimeException")
    public void onFailure(JoinPoint joinPoint, RuntimeException runtimeException) {
        log.info("Payment processing failed with exception: %s (method: %s)".formatted(runtimeException.getClass(), joinPoint.getSignature()));
    }

    @After("bean(processPaymentUseCase)")
    public void onFinish() {
        log.info("Payment processing finished");
    }

}
