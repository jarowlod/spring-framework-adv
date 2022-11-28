package pl.training.shop.payments.service.integration;

import lombok.RequiredArgsConstructor;
import pl.training.shop.commons.Adapter;
import pl.training.shop.payments.persistence.JpaPaymentRepository;
import pl.training.shop.payments.service.model.PaymentIdDomain;
import pl.training.shop.payments.service.model.PaymentDomain;
import pl.training.shop.payments.service.output.PaymentRepository;

import java.util.Optional;

@Adapter
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPaymentRepositoryMapper mapper;

    @Override
    public PaymentDomain save(PaymentDomain paymentDomain) {
        var paymentEntity = mapper.toEntity(paymentDomain);
        return mapper.toDomain(paymentRepository.save(paymentEntity));
    }

    @Override
    public Optional<PaymentDomain> getById(PaymentIdDomain paymentIdDomain) {
        var paymentIdEntity = mapper.toEntity(paymentIdDomain);
        return paymentRepository.getById(paymentIdEntity)
                .map(mapper::toDomain);
    }

}
