package pl.training.broker.payments.service.integration;

import org.mapstruct.Mapper;
import pl.training.broker.payments.persistence.sql.PaymentEntity;
import pl.training.broker.payments.service.PaymentDomain;

@Mapper(componentModel = "spring")
public interface JpaPaymentPersistenceMapper {

    PaymentEntity toEntity(PaymentDomain paymentDomain);

    PaymentDomain toDomain(PaymentEntity paymentEntity);

}
