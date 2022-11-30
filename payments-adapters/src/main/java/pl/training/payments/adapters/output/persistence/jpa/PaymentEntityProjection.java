package pl.training.payments.adapters.output.persistence.jpa;

import org.springframework.beans.factory.annotation.Value;

public interface PaymentEntityProjection {

    String getId();
    String getStatus();
    @Value("#{target.id + ': ' + target.status.toLowerCase()}")
    String getDescription();

}
