package pl.training.shop.payments.service.input;

import pl.training.shop.payments.service.model.PaymentIdDomain;
import pl.training.shop.payments.service.model.PaymentDomain;
import pl.training.shop.payments.service.model.PaymentRequestDomain;

public interface PaymentService {

    PaymentDomain process(PaymentRequestDomain paymentRequestDomain);

    PaymentDomain getById(PaymentIdDomain paymentIdDomain);

}
