package pl.training.payments.adapters.output.persistence.mongodb;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import pl.training.payments.ports.model.*;

@Mapper(componentModel = "spring", imports = {MoneyPort.class})
public interface MongoPaymentRepositoryMapper {

    @Mapping(target = "value", expression = "java(paymentPort.getValue().getValue())")
    @Mapping(target = "currency", expression = "java(paymentPort.getValue().getCurrencySymbol())")
    PaymentDocument toDocument(PaymentPort paymentPort);

    @Mapping(target = "value", expression = "java(new MoneyPort(paymentDocument.getValue(), paymentDocument.getCurrency()))")
    PaymentPort toPort(PaymentDocument paymentDocument);

    String toDocument(PaymentStatusPort paymentStatusPort);

    @Mapping(source = "content", target = "data")
    @Mapping(source = "number", target = "pageNumber")
    ResultPagePort<PaymentPort> toPort(Page<PaymentDocument> paymentDocumentPage);

    default String toDocument(PaymentIdPort paymentIdPort) {
        return paymentIdPort.getValue();
    }

    default PaymentIdPort toPort(String idDocument) {
        return new PaymentIdPort(idDocument);
    }

}
