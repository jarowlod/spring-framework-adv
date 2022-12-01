package pl.training.broker.payments.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@DependsOn("initializer")
@Service
@RequiredArgsConstructor
public class ReactivePaymentService {


    private static final int CACHE_SIZE = 10;

    private final PaymentRepository paymentRepository;
    private final Sinks.Many<PaymentDomain> payments = Sinks.many().replay().all(CACHE_SIZE);

    @PostConstruct
    public void init() {
        paymentRepository.getAll()
                .takeLast(CACHE_SIZE)
                .filter(PaymentDomain::isConfirmed)
                .subscribe(payments::tryEmitNext);
    }

    public Flux<PaymentDomain> getAllPayments() {
        return paymentRepository.getAll();
    }

    public Mono<PaymentDomain> process(Mono<PaymentDomain> paymentDomain) {
        return paymentDomain.map(PaymentDomain::confirmed)
                .flatMap(paymentRepository::persist)
                .doOnNext(payments::tryEmitNext);
    }

    public Flux<PaymentDomain> getProcessedPayments() {
        return payments.asFlux();
    }

}
