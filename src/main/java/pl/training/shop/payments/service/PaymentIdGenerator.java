package pl.training.shop.payments.service;

import pl.training.shop.payments.service.model.PaymentIdDomain;

public interface PaymentIdGenerator {

    PaymentIdDomain getNext();

}
