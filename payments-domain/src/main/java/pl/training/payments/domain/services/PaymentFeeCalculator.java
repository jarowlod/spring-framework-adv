package pl.training.payments.domain.services;

import org.javamoney.moneta.Money;

interface PaymentFeeCalculator {

    Money calculateFee(Money paymentValue);

}
