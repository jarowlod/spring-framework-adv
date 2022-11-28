package pl.training.shop.payments.service.integration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.payments.persistence.PaymentEntity;
import pl.training.shop.payments.service.model.PaymentIdDomain;
import pl.training.shop.payments.service.model.PaymentDomain;

@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class, org.javamoney.moneta.Money.class})
public interface JpaPaymentRepositoryMapper {

    @Mapping(target = "value", expression = "java(BigDecimal.valueOf(paymentDomain.getValue().getNumber().doubleValueExact()))")
    @Mapping(target = "currency", expression = "java(paymentDomain.getValue().getCurrency().getCurrencyCode())")
    PaymentEntity toEntity(PaymentDomain paymentDomain);

    @Mapping(target = "value", expression = "java(Money.of(paymentEntity.getValue(), paymentEntity.getCurrency()))")
    PaymentDomain toDomain(PaymentEntity paymentEntity);

    default String toEntity(PaymentIdDomain paymentIdDomain) {
        return paymentIdDomain.getValue();
    }

    default PaymentIdDomain toDomain(String idEntity) {
        return new PaymentIdDomain(idEntity);
    }

}
