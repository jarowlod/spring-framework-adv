package pl.training.shop.payments.domain;

import java.util.UUID;

public class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public PaymentIdDomain getNext() {
        return new PaymentIdDomain(UUID.randomUUID().toString());
    }

}
