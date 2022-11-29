package pl.training.payments.adapters.logging;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.training.payments.ports.model.PaymentPort;
import pl.training.payments.ports.model.PaymentRequestPort;

@Order(1_00)
@Aspect
@Component
@Log
public class ConsolePaymentsProcessingLogger {

    // Warning aspect on interfaces are dangerous
    //@Pointcut("execution(* pl.training.payments.*.Transactional*.proce*(..))")
    //@Pointcut("execution(pl.training.payments.ports.model.PaymentPort pl.training.payments.adapters.TransactionalPaymentsService.process(pl.training.payments.ports.model.PaymentRequestPort))")
    @Pointcut("@annotation(pl.training.payments.ports.Processing)")
    //@Pointcut("bean(processPaymentUseCase)")
    void process() {
    }

    @Before("process() && args(paymentRequestPort)")
    public void onStart(JoinPoint joinPoint, PaymentRequestPort paymentRequestPort) {
        // var paymentRequestPort = (PaymentRequestPort) joinPoint.getArgs()[0];
        log.info("New payment request: " + paymentRequestPort);
    }

    @AfterReturning(value = "process()", returning = "paymentPort")
    public void onSuccess(PaymentPort paymentPort) {
        log.info("A new payment of %s has been initialized".formatted(paymentPort.getValue()));
    }

    @AfterThrowing(value = "process()", throwing = "runtimeException")
    public void onFailure(JoinPoint joinPoint, RuntimeException runtimeException) {
        log.info("Payment processing failed with exception: %s (method: %s)".formatted(runtimeException.getClass(), joinPoint.getSignature()));
    }

    @After("process()")
    public void onFinish() {
        log.info("Payment processing finished");
    }

}
