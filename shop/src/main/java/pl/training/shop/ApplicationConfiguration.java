package pl.training.shop;

import feign.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.training.payments.adapters.commons.aop.TransactionWrapper;
import pl.training.payments.adapters.output.time.remote.RestTemplateTokenInterceptor;

@EnableFeignClients("pl.training")
//@EnableAspectJAutoProxy
@ComponentScan("pl.training")
@EntityScan("pl.training")
@EnableMongoRepositories("pl.training")
@EnableJpaRepositories(value = "pl.training", repositoryImplementationPostfix = "Custom")
@EnableTransactionManagement(order = 1_000)
@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders("*")
                .allowedMethods("*");
    }

    @Bean
    public TransactionWrapper transactionWrapper(PlatformTransactionManager transactionManager) {
        return new TransactionWrapper(transactionManager);
    }

    @Bean
    public RestTemplate restTemplate(@Value("${timeApi.token}") String token) {
        return new RestTemplateBuilder()
                .additionalInterceptors(new RestTemplateTokenInterceptor(token))
                .build();
    }


}
