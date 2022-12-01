package pl.training.broker.payments.service.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.broker.payments.persistence.mongo.MongoPaymentRepository;
import pl.training.broker.payments.service.PaymentDomain;
import pl.training.broker.payments.service.PaymentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MongoPaymentPersistenceAdapter implements PaymentRepository {

    private final MongoPaymentRepository mongoPaymentRepository;
    private final MongoPaymentPersistenceMapper mapper;

    @Override
    public Mono<PaymentDomain> persist(PaymentDomain paymentDomain) {
        var paymentDocument = mapper.toDocument(paymentDomain);
        return mongoPaymentRepository.save(paymentDocument)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<PaymentDomain> getAll() {
        return mongoPaymentRepository.findAll()
                .map(mapper::toDomain);
    }

}
