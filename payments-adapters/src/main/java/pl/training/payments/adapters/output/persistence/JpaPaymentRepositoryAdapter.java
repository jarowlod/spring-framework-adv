package pl.training.payments.adapters.output.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.training.payments.adapters.commons.Adapter;
import pl.training.payments.ports.model.*;
import pl.training.payments.ports.output.PaymentsReader;
import pl.training.payments.ports.output.PaymentsWriter;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
@Adapter
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentsReader, PaymentsWriter {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPaymentRepositoryMapper mapper;

    @Override
    public Optional<PaymentPort> getById(PaymentIdPort paymentIdPort) {
        var paymentIdEntity = mapper.toEntity(paymentIdPort);
        return paymentRepository.findById(paymentIdEntity)
                .map(mapper::toPort);
    }

    @Override
    public ResultPagePort<PaymentPort> getByStatus(PaymentStatusPort paymentStatusPort, PagePort pagePort) {
        var statusEntity = mapper.toEntity(paymentStatusPort);
        var pageEntity = PageRequest.of(pagePort.getNumber(), pagePort.getSize());
        var resultEntity = paymentRepository.getByStatus(statusEntity, pageEntity);
        return mapper.toPort(resultEntity);
    }

    @Override
    public PaymentPort persist(PaymentPort paymentPort) {
        var paymentEntity = mapper.toEntity(paymentPort);
        return mapper.toPort(paymentRepository.save(paymentEntity));
    }

}
