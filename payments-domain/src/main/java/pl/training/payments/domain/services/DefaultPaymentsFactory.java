package pl.training.payments.domain.services;

import org.mapstruct.factory.Mappers;
import pl.training.payments.adapters.PaymentsDomainMapper;
import pl.training.payments.adapters.input.GetPaymentUseCaseAdapter;
import pl.training.payments.adapters.input.ProcessPaymentUseCaseAdapter;
import pl.training.payments.adapters.output.PaymentsReaderAdapter;
import pl.training.payments.adapters.output.PaymentsWriterAdapter;
import pl.training.payments.ports.PaymentsFactory;
import pl.training.payments.ports.input.GetPaymentUseCase;
import pl.training.payments.ports.input.ProcessPaymentUseCase;
import pl.training.payments.ports.output.PaymentsReader;
import pl.training.payments.ports.output.PaymentsWriter;
import pl.training.payments.ports.output.TimeProvider;

public class DefaultPaymentsFactory implements PaymentsFactory {

    private final PaymentsDomainMapper paymentsDomainMapper = Mappers.getMapper(PaymentsDomainMapper.class);
    private final PaymentIdGenerator paymentIdGenerator = new UuidPaymentIdGenerator();
    private final PaymentFeeCalculator paymentFeeCalculator;

    public DefaultPaymentsFactory(double feePercentageValue) {
        paymentFeeCalculator = new PercentagePaymentFeeCalculator(feePercentageValue);
    }

    @Override
    public GetPaymentUseCase getPaymentUseCase(PaymentsReader paymentsReader) {
        var paymentsReaderAdapter = new PaymentsReaderAdapter(paymentsReader, paymentsDomainMapper);
        var getPaymentService = new GetPaymentService(paymentsReaderAdapter);
        return new GetPaymentUseCaseAdapter(getPaymentService, paymentsDomainMapper);
    }

    @Override
    public ProcessPaymentUseCase processPaymentUseCase(PaymentsWriter paymentsWriter, TimeProvider timeProvider) {
        var paymentsWriterAdapter = new PaymentsWriterAdapter(paymentsWriter, paymentsDomainMapper);
        var processPaymentService = new ProcessPaymentService(paymentIdGenerator, paymentFeeCalculator, paymentsWriterAdapter, timeProvider);
        return new ProcessPaymentUseCaseAdapter(processPaymentService, paymentsDomainMapper);
    }

}
