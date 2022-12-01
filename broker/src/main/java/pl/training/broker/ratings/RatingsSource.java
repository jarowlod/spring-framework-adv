package pl.training.broker.ratings;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.util.function.Tuple2;

import java.math.BigDecimal;
import java.util.Random;

import static java.time.Duration.ofSeconds;
import static reactor.core.publisher.Flux.generate;
import static reactor.core.publisher.Flux.interval;

@Component
public class RatingsSource {

    private final Random random = new Random();
    private final double MAX_RATING_FLUCTUATION = 0.5;
    private final int RATING_UPDATE_INTERVAL_IN_SECONDS = 3;

    public Flux<BigDecimal> getExchangeRates() {
        return interval(ofSeconds(RATING_UPDATE_INTERVAL_IN_SECONDS))
                .zipWith(generate(this::nextRating).map(BigDecimal::valueOf))
                .map(Tuple2::getT2);
    }

    private void nextRating(SynchronousSink<Double> sink) {
        sink.next(random.nextDouble(MAX_RATING_FLUCTUATION) * (random.nextBoolean() ? 1 : -1));
    }

}
