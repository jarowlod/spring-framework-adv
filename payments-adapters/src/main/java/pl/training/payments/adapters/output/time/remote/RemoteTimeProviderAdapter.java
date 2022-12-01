package pl.training.payments.adapters.output.time.remote;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClientException;
import pl.training.payments.adapters.commons.Adapter;
import pl.training.payments.ports.model.ServiceUnavailableException;
import pl.training.payments.ports.output.TimeProvider;

import java.time.Instant;

@Primary
@Adapter
@RequiredArgsConstructor
public class RemoteTimeProviderAdapter implements TimeProvider {

    private final RestTemplateTimeProvider timeProvider;
    private final RemoteTimeRestMapper mapper;

    @Override
    public Instant getTimestamp() {
        try {
            return timeProvider.getTime()
                    .map(mapper::toPort)
                    .orElseThrow(ServiceUnavailableException::new);
        } catch (RestClientException restClientException) {
            throw new ServiceUnavailableException();
        }
    }

}
