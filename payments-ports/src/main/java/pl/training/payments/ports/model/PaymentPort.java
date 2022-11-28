package pl.training.payments.ports.model;

import lombok.Value;

import java.time.Instant;

@Value
public class PaymentPort {

    PaymentIdPort id;
    MoneyPort value;
    Instant timestamp;
    PaymentStatusPort status;

}
