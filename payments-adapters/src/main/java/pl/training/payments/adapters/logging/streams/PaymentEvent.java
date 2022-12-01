package pl.training.payments.adapters.logging.streams;

import lombok.Data;

@Data
public class PaymentEvent {

    private String id;
    private String value;
    private String status;

}
