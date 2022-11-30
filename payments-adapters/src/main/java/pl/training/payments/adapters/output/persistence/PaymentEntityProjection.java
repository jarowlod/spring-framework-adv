package pl.training.payments.adapters.output.persistence;

import org.springframework.beans.factory.annotation.Value;

public interface PaymentEntityProjection {

    String getId();
    String getStatus();
    @Value("#{target.id + ': ' + target.status.toLowerCase()}")
    String getDescription();

}
