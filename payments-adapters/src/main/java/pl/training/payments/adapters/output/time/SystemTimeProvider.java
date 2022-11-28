package pl.training.payments.adapters.output.time;

import org.springframework.stereotype.Service;
import pl.training.payments.ports.output.TimeProvider;

import java.time.Instant;

@Service
public class SystemTimeProvider implements TimeProvider {

    @Override
    public Instant getTimestamp() {
        return Instant.now();
    }

}
