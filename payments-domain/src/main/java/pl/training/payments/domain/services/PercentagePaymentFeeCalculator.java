package pl.training.payments.domain.services;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class PercentagePaymentFeeCalculator implements PaymentFeeCalculator {

    private final double percentage;

    @Override
    public Money calculateFee(Money paymentValue) {
        return paymentValue.multiply(percentage);
    }

}
