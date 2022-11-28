package pl.training.payments.domain.services;

import lombok.RequiredArgsConstructor;
import pl.training.payments.adapters.output.PaymentsReaderAdapter;
import pl.training.payments.domain.model.*;
import pl.training.payments.ports.model.PaymentNotFoundException;

@RequiredArgsConstructor
public class GetPaymentService {

    private final PaymentsReaderAdapter paymentsReader;

    public PaymentDomain getById(PaymentIdDomain paymentIdDomain) {
        return paymentsReader.getById(paymentIdDomain)
                .orElseThrow(PaymentNotFoundException::new);
    }

    public ResultPageDomain<PaymentDomain> getByStatus(PaymentStatusDomain paymentStatusDomain, PageDomain pageDomain) {
        return paymentsReader.getByStatus(paymentStatusDomain, pageDomain);
    }

}
