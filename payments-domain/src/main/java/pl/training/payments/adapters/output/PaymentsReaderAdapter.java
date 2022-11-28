package pl.training.payments.adapters.output;

import lombok.RequiredArgsConstructor;
import pl.training.payments.adapters.PaymentsDomainMapper;
import pl.training.payments.domain.model.*;
import pl.training.payments.ports.output.PaymentsReader;

import java.util.Optional;

@RequiredArgsConstructor
public class PaymentsReaderAdapter {

    private final PaymentsReader paymentsReader;
    private final PaymentsDomainMapper mapper;

    public Optional<PaymentDomain> getById(PaymentIdDomain paymentIdDomain) {
        var paymentIdPort = mapper.toPort(paymentIdDomain);
        return paymentsReader.getById(paymentIdPort)
                .map(mapper::toDomain);
    }

    public ResultPageDomain<PaymentDomain> getByStatus(PaymentStatusDomain paymentStatusDomain, PageDomain pageDomain) {
        var paymentStatusPort = mapper.toPort(paymentStatusDomain);
        var pagePort = mapper.toPort(pageDomain);
        var resultPagePort = paymentsReader.getByStatus(paymentStatusPort, pagePort);
        return mapper.toDomain(resultPagePort);
    }

}
