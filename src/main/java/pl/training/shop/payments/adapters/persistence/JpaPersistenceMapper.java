package pl.training.shop.payments.adapters.persistence;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.training.shop.commons.data.ResultPage;
import pl.training.shop.payments.domain.PaymentDomain;
import pl.training.shop.payments.domain.PaymentStatusDomain;

import java.util.List;

@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class, org.javamoney.moneta.FastMoney.class})
public interface JpaPersistenceMapper {

    @Mapping(target = "value", expression = "java(BigDecimal.valueOf(paymentDomain.getValue().getNumber().doubleValueExact()))")
    @Mapping(target = "currency", expression = "java(paymentDomain.getValue().getCurrency().getCurrencyCode())")
    PaymentEntity toEntity(PaymentDomain paymentDomain);

    @Mapping(target = "value", expression = "java(FastMoney.of(paymentEntity.getValue(), paymentEntity.getCurrency()))")
    PaymentDomain toDomain(PaymentEntity paymentEntity);

    String toEntity(PaymentStatusDomain paymentStatusDomain);

    @IterableMapping(elementTargetType = PaymentDomain.class)
    List<PaymentDomain> toDomain(List<PaymentEntity> paymentEntities);

    @Mapping(source = "content", target = "data")
    @Mapping(source = "number", target = "pageNumber")
    ResultPage<PaymentDomain> toDomain(Page<PaymentEntity> paymentEntityPage);

}
