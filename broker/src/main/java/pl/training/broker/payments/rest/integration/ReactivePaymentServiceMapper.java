package pl.training.broker.payments.rest.integration;

import org.mapstruct.Mapper;
import pl.training.broker.payments.rest.PaymentEvent;
import pl.training.broker.payments.service.PaymentDomain;

@Mapper(componentModel = "spring")
public interface ReactivePaymentServiceMapper {

    PaymentDomain toDomain(PaymentEvent paymentEvent);

    PaymentEvent toEvent(PaymentDomain paymentDomain);

}
