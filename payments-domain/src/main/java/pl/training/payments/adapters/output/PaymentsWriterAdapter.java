package pl.training.payments.adapters.output;

import lombok.RequiredArgsConstructor;
import pl.training.payments.adapters.PaymentsDomainMapper;
import pl.training.payments.domain.model.PaymentDomain;
import pl.training.payments.ports.output.PaymentsWriter;

@RequiredArgsConstructor
public class PaymentsWriterAdapter {

    private final PaymentsWriter paymentsWriter;
    private final PaymentsDomainMapper mapper;

    public PaymentDomain save(PaymentDomain paymentDomain) {
        var paymentPort = mapper.toPort(paymentDomain);
        return mapper.toDomain(paymentsWriter.persist(paymentPort));
    }

}
