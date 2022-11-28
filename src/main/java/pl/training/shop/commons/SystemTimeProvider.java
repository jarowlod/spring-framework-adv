package pl.training.shop.commons;

import org.springframework.stereotype.Service;
import pl.training.shop.payments.service.output.TimeProvider;

import java.time.Instant;

@Service
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
