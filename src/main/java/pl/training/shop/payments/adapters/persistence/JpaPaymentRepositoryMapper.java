package pl.training.shop.payments.adapters.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentIdDomain;
import pl.training.shop.payments.domain.PaymentStatusDomain;

@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class, org.javamoney.moneta.Money.class})
public interface JpaPaymentRepositoryMapper {

    @Mapping(target = "value", expression = "java(paymentDomain.getValue().getNumberStripped())")
    @Mapping(target = "currency", expression = "java(paymentDomain.getValue().getCurrency().getCurrencyCode())")
    PaymentEntity toEntity(PaymentDomain paymentDomain);

    @Mapping(target = "value", expression = "java(Money.of(paymentEntity.getValue(), paymentEntity.getCurrency()))")
    PaymentDomain toDomain(PaymentEntity paymentEntity);

    String toEntity(PaymentStatusDomain paymentStatusDomain);

    @Mapping(source = "content", target = "data")
    @Mapping(source = "number", target = "pageNumber")
    ResultPage<PaymentDomain> toDomain(Page<PaymentEntity> paymentEntityPage);

    default String toEntity(PaymentIdDomain paymentIdDomain) {
        return paymentIdDomain.getValue();
    }

    default PaymentIdDomain toDomain(String idEntity) {
        return new PaymentIdDomain(idEntity);
    }

}
