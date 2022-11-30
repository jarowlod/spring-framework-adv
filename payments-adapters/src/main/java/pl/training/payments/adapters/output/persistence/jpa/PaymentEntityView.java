package pl.training.payments.adapters.output.persistence.jpa;

import lombok.Value;

@Value
public class PaymentEntityView {

    String id;
    String status;

}
