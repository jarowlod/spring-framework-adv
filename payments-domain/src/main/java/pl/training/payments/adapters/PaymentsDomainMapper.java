package pl.training.payments.adapters;

import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;
import pl.training.payments.domain.model.*;
import pl.training.payments.ports.model.*;

@Mapper
public interface PaymentsDomainMapper {

    PaymentIdDomain toDomain(PaymentIdPort paymentIdPort);

    PaymentIdPort toPort(PaymentIdDomain paymentIdDomain);

    PaymentPort toPort(PaymentDomain paymentDomain);

    default MoneyPort toPort(Money money) {
        return new MoneyPort(money.getNumberStripped(), money.getCurrency().getCurrencyCode());
    }

    PaymentStatusDomain toDomain(PaymentStatusPort paymentStatusPort);

    PaymentStatusPort toPort(PaymentStatusDomain paymentStatusDomain);

    PageDomain toDomain(PagePort pagePort);

    PagePort toPort(PageDomain pageDomain);

    ResultPagePort<PaymentPort> toPort(ResultPageDomain<PaymentDomain> resultPageDomain);

    ResultPageDomain<PaymentDomain> toDomain(ResultPagePort<PaymentPort> resultPageDomain);

    PaymentRequestDomain toDomain(PaymentRequestPort paymentRequestPort);

    PaymentDomain toDomain(PaymentPort paymentPort);

}
