package pl.training.shop.payments.service.model;

import lombok.Value;
import org.javamoney.moneta.Money;

@Value
public class PaymentRequestDomain {

    Long id;
    Money value;

}
