package pl.training.chat.integration.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class CacheExamples implements ApplicationRunner {

    private final Calculator calculator;
    private final CacheManager cacheManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("First attempt: " + calculator.add(1, 2));
        log.info("Second attempt: " + calculator.add(1, 2));
        //calculator.reset("add12");
        Optional.ofNullable(cacheManager.getCache("results")).ifPresent(cache -> {
            //cache.clear();
            cache.evictIfPresent("add12");
        });
        log.info("Third attempt: " + calculator.add(1, 2));
    }

}
