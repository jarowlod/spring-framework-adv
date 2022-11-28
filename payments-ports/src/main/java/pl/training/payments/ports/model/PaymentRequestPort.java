package pl.training.payments.ports.model;

import lombok.Value;

@Value
public class PaymentRequestPort {

    PaymentRequestIdPort id;
    MoneyPort value;

}
