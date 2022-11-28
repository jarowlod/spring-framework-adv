package pl.training.payments.domain.model;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.Money;

import java.time.Instant;

@Builder
@Value
public class PaymentDomain {

    PaymentIdDomain id;
    Money value;
    Instant timestamp;
    PaymentStatusDomain status;

}
