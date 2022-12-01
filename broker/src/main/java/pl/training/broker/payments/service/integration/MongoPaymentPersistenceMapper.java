package pl.training.broker.payments.service.integration;

import org.mapstruct.Mapper;
import pl.training.broker.payments.persistence.mongo.PaymentDocument;
import pl.training.broker.payments.service.PaymentDomain;

@Mapper(componentModel = "spring")
public interface MongoPaymentPersistenceMapper {

    PaymentDomain toDomain(PaymentDocument paymentDocument);

    PaymentDocument toDocument(PaymentDomain paymentDomain);

}
