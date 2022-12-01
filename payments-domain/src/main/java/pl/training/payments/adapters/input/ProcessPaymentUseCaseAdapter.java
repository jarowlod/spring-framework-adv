package pl.training.payments.adapters.input;

import lombok.RequiredArgsConstructor;
import pl.training.commons.aop.Atomic;
import pl.training.payments.adapters.PaymentsDomainMapper;
import pl.training.payments.domain.services.ProcessPaymentService;
import pl.training.payments.ports.Processing;
import pl.training.payments.ports.input.ProcessPaymentUseCase;
import pl.training.payments.ports.model.PaymentPort;
import pl.training.payments.ports.model.PaymentRequestPort;

@RequiredArgsConstructor
public class ProcessPaymentUseCaseAdapter implements ProcessPaymentUseCase {

    private final ProcessPaymentService processPaymentService;
    private final PaymentsDomainMapper mapper;

    @Processing
    @Atomic
    @Override
    public PaymentPort process(PaymentRequestPort paymentRequestPort) {
        var paymentRequestDomain = mapper.toDomain(paymentRequestPort);
        var paymentDomain = processPaymentService.process(paymentRequestDomain);
        return mapper.toPort(paymentDomain);
    }

}
