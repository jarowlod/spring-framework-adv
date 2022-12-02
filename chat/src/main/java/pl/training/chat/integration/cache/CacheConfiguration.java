package pl.training.chat.integration.cache;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class CacheConfiguration {

    /*@Bean
    public CacheManager cacheManager() {
        return new TransactionAwareCacheManagerProxy(new ConcurrentMapCacheManager("results"));
    }*/

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        var config = RedisCacheConfiguration.defaultCacheConfig();
        config.usePrefix();
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
    }

}
