package pl.training.payments.adapters;

import lombok.RequiredArgsConstructor;
import pl.training.payments.domain.model.*;
import pl.training.payments.ports.output.PaymentsReader;

import java.util.Optional;

@RequiredArgsConstructor
public class PaymentsReaderAdapter {

    private final PaymentsReader paymentsReader;

    public Optional<PaymentDomain> getById(PaymentIdDomain paymentIdDomain) {
        return Optional.empty();
    }

    public ResultPageDomain<PaymentDomain> getByStatus(PaymentStatusDomain paymentStatusDomain, PageDomain pageDomain) {
        return new ResultPageDomain<>(null, 1, 1 );
    }

}
