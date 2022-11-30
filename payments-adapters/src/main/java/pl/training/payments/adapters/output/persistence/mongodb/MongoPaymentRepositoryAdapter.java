package pl.training.payments.adapters.output.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.training.payments.adapters.commons.Adapter;
import pl.training.payments.ports.model.*;
import pl.training.payments.ports.output.PaymentsReader;
import pl.training.payments.ports.output.PaymentsWriter;

import java.util.Optional;

@Primary
@Transactional(propagation = Propagation.MANDATORY)
@Adapter
@RequiredArgsConstructor
public class MongoPaymentRepositoryAdapter implements PaymentsReader, PaymentsWriter {

    private final MongoPaymentRepository paymentRepository;
    private final MongoPaymentRepositoryMapper mapper;

    @Override
    public Optional<PaymentPort> getById(PaymentIdPort paymentIdPort) {
        var paymentIdDocument = mapper.toDocument(paymentIdPort);
        return paymentRepository.findById(paymentIdDocument)
                .map(mapper::toPort);
    }

    @Override
    public ResultPagePort<PaymentPort> getByStatus(PaymentStatusPort paymentStatusPort, PagePort pagePort) {
        var statusDocument = mapper.toDocument(paymentStatusPort);
        var pageDocument = PageRequest.of(pagePort.getNumber(), pagePort.getSize());
        var resultDocument = paymentRepository.getByStatus(statusDocument, pageDocument);
        return mapper.toPort(resultDocument);
    }

    @Override
    public PaymentPort persist(PaymentPort paymentPort) {
        var paymentDocument = mapper.toDocument(paymentPort);
        return mapper.toPort(paymentRepository.save(paymentDocument));
    }

}
