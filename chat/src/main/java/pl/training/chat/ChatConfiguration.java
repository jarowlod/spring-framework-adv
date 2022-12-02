package pl.training.chat;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableCaching(order = 100_000)
@EnableAsync
@EnableScheduling
@EnableWebSocketMessageBroker
@Configuration
public class ChatConfiguration implements WebSocketMessageBrokerConfigurer, AsyncConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/main-room", "/private-rooms");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/chat")
               .addInterceptors(new WebSocketHandshakeInterceptor())
               .withSockJS();
    }

    /*@Override
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(10);
    }*/


    @Bean
    public Executor mailExecutor() {
        return Executors.newFixedThreadPool(10);
    }

}
