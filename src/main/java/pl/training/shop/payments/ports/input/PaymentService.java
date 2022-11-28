package pl.training.shop.payments.ports.input;

import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentIdDomain;
import pl.training.shop.payments.domain.PaymentRequestDomain;

public interface PaymentService {

    PaymentDomain process(PaymentRequestDomain paymentRequestDomain);

    PaymentDomain getById(PaymentIdDomain paymentIdDomain);

}
