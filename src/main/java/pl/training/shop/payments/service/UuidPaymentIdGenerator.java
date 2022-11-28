package pl.training.shop.payments.service;

import org.springframework.stereotype.Service;
import pl.training.shop.payments.service.model.PaymentIdDomain;

import java.util.UUID;

@Service
public class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public PaymentIdDomain getNext() {
        return new PaymentIdDomain(UUID.randomUUID().toString());
    }

}
