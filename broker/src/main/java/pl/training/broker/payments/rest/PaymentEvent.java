package pl.training.broker.payments.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Value
public class PaymentEvent {

    @JsonProperty(access = READ_ONLY)
    String id;
    String value;
    @JsonProperty(access = READ_ONLY)
    String status;

}
