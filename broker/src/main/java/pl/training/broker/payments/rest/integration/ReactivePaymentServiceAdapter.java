package pl.training.broker.payments.rest.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.training.broker.payments.rest.PaymentEvent;
import pl.training.broker.payments.service.ReactivePaymentService;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class ReactivePaymentServiceAdapter {

    private final ReactivePaymentService paymentService;
    private final ReactivePaymentServiceMapper mapper;

    public Mono<ServerResponse> process(ServerRequest serverRequest) {
        var paymentDomain = serverRequest.bodyToMono(PaymentEvent.class).map(mapper::toDomain);
        var paymentEvent = paymentService.process(paymentDomain).map(mapper::toEvent);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(paymentEvent, PaymentEvent.class);
    }

    public Mono<ServerResponse> getAllPayments(ServerRequest serverRequest) {
        var paymentEvents = paymentService.getAllPayments().map(mapper::toEvent);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(paymentEvents, PaymentEvent.class);
    }

    public Mono<ServerResponse> getProcessedPayments(ServerRequest serverRequest) {
        var paymentEvents = paymentService.getProcessedPayments().map(mapper::toEvent);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(paymentEvents, PaymentEvent.class);
    }

}
