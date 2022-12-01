package pl.training.broker.payments.service.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.training.broker.payments.persistence.sql.JpaPaymentRepository;
import pl.training.broker.payments.service.PaymentDomain;
import pl.training.broker.payments.service.PaymentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Primary
@Component
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPaymentPersistenceMapper mapper;

    @Override
    public Mono<PaymentDomain> persist(PaymentDomain paymentDomain) {
        var entity = mapper.toEntity(paymentDomain);
        return paymentRepository.save(entity)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<PaymentDomain> getAll() {
        return paymentRepository.findAll()
                .map(mapper::toDomain);
    }

}
