package pl.training.shop.payments.adapters.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentIdDomain;

@Mapper(componentModel = "spring", imports = {org.javamoney.moneta.Money.class})
public interface JpaPaymentRepositoryMapper {

    @Mapping(target = "value", expression = "java(paymentDomain.getValue().getNumberStripped())")
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
