package pl.training.chat.integration.cache;

import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@CacheConfig(cacheNames = "results", keyGenerator = "cacheKeyGenerator")
@Service
@Log
public class Calculator {

    @Cacheable//(condition = "#a < #b")
    // @Cacheable(value = "results", keyGenerator = "cacheKeyGenerator")
    public int add(int a, int b) {
        log.info("Calculating sum");
        return a + b;
    }

    @CacheEvict(/*key = "#key",*/ allEntries = true/*, condition = "true"*/)
    //@CacheEvict(value = "results", key = "#key")
    public void reset(String key) {
        log.info("Resetting cache for key: " + key);
    }

    @CachePut(cacheNames = "results", key = "#key")
    public int updateResult(String key) {
        return 10;
    }

}
