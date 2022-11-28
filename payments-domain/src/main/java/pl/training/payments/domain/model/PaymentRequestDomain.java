package pl.training.payments.domain.model;

import lombok.Value;
import org.javamoney.moneta.Money;

@Value
public class PaymentRequestDomain {

    Long id;
    Money value;

}
