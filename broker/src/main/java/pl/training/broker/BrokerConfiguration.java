package pl.training.broker;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import pl.training.broker.payments.rest.integration.ReactivePaymentServiceAdapter;
import pl.training.broker.ratings.integration.WebSocketRatingsSourceAdapter;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BrokerConfiguration {

    private static final String RATINGS_ENDPOINT = "ratings";
    private static final String PAYMENTS_ENDPOINT = "payments";
    private static final String PROCESSED_PAYMENTS_ENDPOINT = PAYMENTS_ENDPOINT + "/processed";

    @Bean
    public RouterFunction<ServerResponse> routes(ReactivePaymentServiceAdapter adapter) {
        return RouterFunctions
                .route(GET(PAYMENTS_ENDPOINT).and(accept(APPLICATION_JSON)), adapter::getAllPayments)
                .andRoute(GET(PROCESSED_PAYMENTS_ENDPOINT ).and(accept(APPLICATION_JSON)), adapter::getProcessedPayments)
                .andRoute(POST(PAYMENTS_ENDPOINT).and(accept(APPLICATION_JSON)), adapter::process);
    }

    @Bean
    public HandlerMapping handlerMapping(WebSocketRatingsSourceAdapter adapter) {
        var handlerMapper = new SimpleUrlHandlerMapping();
        handlerMapper.setOrder(1);
        handlerMapper.setUrlMap(Map.of(RATINGS_ENDPOINT, adapter));
        return handlerMapper;
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("init.sql")));
        return initializer;
    }

}
