package pl.training.payments.adapters.logging.streams;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.training.payments.ports.model.PaymentPort;

@Aspect
@Component
@Log
@RequiredArgsConstructor
public class StreamPaymentBrokerAdapter {

    @Value("${brokerApi.endpoint}")
    @Setter
    private String brokerUri;

    private final StreamPaymentMapper mapper;

    @AfterReturning(value = "@annotation(pl.training.payments.ports.Processing)", returning = "paymentPort")
    public void onSuccess(PaymentPort paymentPort) {
        var paymentEvent = mapper.toEvent(paymentPort);
        WebClient.builder()
                .build()
                .post()
                .uri(brokerUri)
                .bodyValue(paymentEvent)
                .retrieve()
                .bodyToMono(PaymentEvent.class)
                .map(mapper::toPort)
                .subscribe(processedPayment -> log.info("Payment processed: " + processedPayment), throwable -> log.warning(throwable.toString()));
    }

}
