package pl.training.broker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.training.broker.payments.rest.integration.ReactivePaymentServiceAdapter;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BrokerConfiguration {

    private static final String PAYMENTS_ENDPOINT = "payments";
    private static final String PROCESSED_PAYMENTS_ENDPOINT = PAYMENTS_ENDPOINT + "/processed";

    @Bean
    public RouterFunction<ServerResponse> routes(ReactivePaymentServiceAdapter adapter) {
        return RouterFunctions
                .route(GET(PAYMENTS_ENDPOINT).and(accept(APPLICATION_JSON)), adapter::getAllPayments)
                .andRoute(GET(PROCESSED_PAYMENTS_ENDPOINT ).and(accept(APPLICATION_JSON)), adapter::getProcessedPayments)
                .andRoute(POST(PAYMENTS_ENDPOINT).and(accept(APPLICATION_JSON)), adapter::process);
    }

}
