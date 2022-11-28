package pl.training.shop.payments.service.output;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
