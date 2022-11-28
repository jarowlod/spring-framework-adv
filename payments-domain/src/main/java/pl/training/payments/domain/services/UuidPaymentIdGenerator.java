package pl.training.payments.domain.services;

import pl.training.payments.domain.model.PaymentIdDomain;

import java.util.UUID;

class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public PaymentIdDomain getNext() {
        return new PaymentIdDomain(UUID.randomUUID().toString());
    }

}
