package pl.training.shop.payments.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.commons.Adapter;
import pl.training.shop.commons.data.Page;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentIdDomain;
import pl.training.shop.payments.domain.PaymentStatusDomain;
import pl.training.shop.payments.ports.PaymentRepository;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
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
        return paymentRepository.findById(paymentIdEntity)
                .map(mapper::toDomain);
    }

    @Override
    public ResultPage<PaymentDomain> getByStatus(PaymentStatusDomain paymentStatusDomain, Page page) {
        var statusEntity = mapper.toEntity(paymentStatusDomain);
        var resultEntity = paymentRepository.getByStatus(statusEntity, PageRequest.of(page.getNumber(), page.getSize()));
        return mapper.toDomain(resultEntity);
    }

}
