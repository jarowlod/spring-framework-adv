package pl.training.payments.adapters.commons.web;

import lombok.Value;

import java.time.Instant;

@Value
public class ExceptionDto {

    Instant timestamp;
    String description;

}
