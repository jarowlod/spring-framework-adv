package pl.training.shop;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.training.payments.adapters.commons.aop.TransactionWrapper;

//@EnableAspectJAutoProxy
@ComponentScan("pl.training")
@EntityScan("pl.training")
@EnableJpaRepositories("pl.training")
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

}
