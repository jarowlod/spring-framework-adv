package pl.training.broker.payments.service;

import lombok.Value;
import lombok.With;

@Value
public class PaymentDomain {

    private static final String CONFIRMED_STATUS = "CONFIRMED";

    String id;
    String value;
    @With
    String status;

    public boolean isConfirmed() {
        return status.equals(CONFIRMED_STATUS);
    }

    public PaymentDomain confirmed() {
        return withStatus(CONFIRMED_STATUS);
    }

}
