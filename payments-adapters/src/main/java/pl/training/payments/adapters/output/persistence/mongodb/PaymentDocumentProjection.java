package pl.training.payments.adapters.output.persistence.mongodb;

import org.springframework.beans.factory.annotation.Value;

public interface PaymentDocumentProjection {

    String getId();
    String getStatus();
    @Value("#{target.id + ': ' + target.status.toLowerCase()}")
    String getDescription();

}
