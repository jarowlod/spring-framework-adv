package pl.training.broker.ratings.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import pl.training.broker.ratings.RatingsSource;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
@Log
@RequiredArgsConstructor
public class WebSocketRatingsSourceAdapter implements WebSocketHandler {

    private final RatingsSource ratingsSource;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        var outputMessages = ratingsSource.getExchangeRates()
                .map(BigDecimal::toString)
                .map(session::textMessage);
        return session.send(outputMessages)
                .and(session.receive().map(WebSocketMessage::getPayloadAsText).doOnNext(this::onMessage));
    }

    private void onMessage(String message) {
        log.info("New message: " + message);
    }

}
