package pl.training.payments.adapters.logging.streams;

import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Log
public class ConsolePaymentStreamLogger {

    @Value(("${brokerApi.endpoint}/processed"))
    @Setter
    public String brokerUrl;

    @PostConstruct
    public void init() {
        WebClient.builder()
                .build()
                .get()
                .uri(brokerUrl)
                .retrieve()
                .bodyToFlux(PaymentEvent.class)
                .subscribe(paymentEvent -> log.info("Payment updated: " + paymentEvent), throwable -> log.warning(throwable.toString()));
    }

}
