package pl.training.payments.domain.services;

import pl.training.payments.domain.model.PaymentIdDomain;

interface PaymentIdGenerator {

    PaymentIdDomain getNext();

}
