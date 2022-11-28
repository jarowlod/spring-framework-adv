package pl.training.payments.ports.model;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class MoneyPort {

    BigDecimal value;
    String currencySymbol;

}
