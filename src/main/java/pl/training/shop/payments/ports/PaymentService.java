package pl.training.shop.payments.ports;

import pl.training.shop.commons.data.Page;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentIdDomain;
import pl.training.shop.payments.domain.PaymentRequestDomain;
import pl.training.shop.payments.domain.PaymentStatusDomain;

public interface PaymentService {

    PaymentDomain process(PaymentRequestDomain paymentRequestDomain);

    PaymentDomain getById(PaymentIdDomain paymentIdDomain);

    ResultPage<PaymentDomain> getByStatus(PaymentStatusDomain paymentStatusDomain, Page page);

}
