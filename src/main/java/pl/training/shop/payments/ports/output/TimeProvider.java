package pl.training.shop.payments.ports.output;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();

}
