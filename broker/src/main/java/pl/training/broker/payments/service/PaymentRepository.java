package pl.training.broker.payments.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentRepository {

    Mono<PaymentDomain> persist(PaymentDomain paymentDomain);

    Flux<PaymentDomain> getAll();

}
