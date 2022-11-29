package pl.training.payments.adapters.input;

import lombok.RequiredArgsConstructor;
import pl.training.commons.aop.Atomic;
import pl.training.payments.adapters.PaymentsDomainMapper;
import pl.training.payments.domain.services.GetPaymentService;
import pl.training.payments.ports.input.GetPaymentUseCase;
import pl.training.payments.ports.model.*;

@Atomic
@RequiredArgsConstructor
public class GetPaymentUseCaseAdapter implements GetPaymentUseCase {

    private final GetPaymentService getPaymentService;
    private final PaymentsDomainMapper mapper;

    @Override
    public PaymentPort getById(PaymentIdPort paymentIdPort) {
        var paymentIdDomain = mapper.toDomain(paymentIdPort);
        var paymentDomain= getPaymentService.getById(paymentIdDomain);
        return mapper.toPort(paymentDomain);
    }

    @Override
    public ResultPagePort<PaymentPort> getByStatus(PaymentStatusPort paymentStatusPort, PagePort pagePort) {
        var paymentStatusDomain = mapper.toDomain(paymentStatusPort);
        var pageDomain = mapper.toDomain(pagePort);
        var resultPageDomain = getPaymentService.getByStatus(paymentStatusDomain, pageDomain);
        return mapper.toPort(resultPageDomain);
    }

}
