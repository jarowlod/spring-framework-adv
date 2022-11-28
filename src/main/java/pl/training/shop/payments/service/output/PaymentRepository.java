package pl.training.shop.payments.service.output;

import pl.training.shop.payments.service.model.PaymentIdDomain;
import pl.training.shop.payments.service.model.PaymentDomain;

import java.util.Optional;

public interface PaymentRepository {

    PaymentDomain save(PaymentDomain paymentDomain);

    Optional<PaymentDomain> getById(PaymentIdDomain paymentIdDomain);

}
