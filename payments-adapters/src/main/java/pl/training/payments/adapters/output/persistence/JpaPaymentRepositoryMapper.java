package pl.training.payments.adapters.output.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.training.payments.ports.model.*;

@Mapper(componentModel = "spring", imports = {MoneyPort.class})
public interface JpaPaymentRepositoryMapper {

    @Mapping(target = "value", expression = "java(paymentPort.getValue().getValue())")
    @Mapping(target = "currency", expression = "java(paymentPort.getValue().getCurrencySymbol())")
    PaymentEntity toEntity(PaymentPort paymentPort);

    @Mapping(target = "value", expression = "java(new MoneyPort(paymentEntity.getValue(), paymentEntity.getCurrency()))")
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
