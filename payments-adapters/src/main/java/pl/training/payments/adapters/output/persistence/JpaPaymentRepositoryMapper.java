package pl.training.payments.adapters.output.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.training.payments.ports.model.PaymentIdPort;
import pl.training.payments.ports.model.PaymentPort;
import pl.training.payments.ports.model.PaymentStatusPort;
import pl.training.payments.ports.model.ResultPagePort;

@Mapper(componentModel = "spring", imports = {org.javamoney.moneta.Money.class})
public interface JpaPaymentRepositoryMapper {

    @Mapping(target = "value", expression = "java(paymentDomain.getValue().getNumberStripped())")
    @Mapping(target = "currency", expression = "java(paymentDomain.getValue().getCurrency().getCurrencyCode())")
    PaymentEntity toEntity(PaymentPort paymentPort);

    @Mapping(target = "value", expression = "java(Money.of(paymentEntity.getValue(), paymentEntity.getCurrency()))")
    PaymentPort toPort(PaymentEntity paymentEntity);

    String toEntity(PaymentStatusPort paymentStatusPort);

    @Mapping(source = "content", target = "data")
    @Mapping(source = "number", target = "pageNumber")
    ResultPagePort<PaymentPort> toPort(Page<PaymentEntity> paymentEntityPage);

    default String toEntity(PaymentIdPort paymentIdPort) {
        return paymentIdPort.getValue();
    }

    default PaymentIdPort toPort(String idEntity) {
        return new PaymentIdPort(idEntity);
    }

}
