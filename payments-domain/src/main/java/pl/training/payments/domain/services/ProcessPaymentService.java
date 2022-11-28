package pl.training.payments.domain.services;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import pl.training.payments.adapters.output.PaymentsWriterAdapter;
import pl.training.payments.domain.model.PaymentDomain;
import pl.training.payments.domain.model.PaymentRequestDomain;
import pl.training.payments.domain.model.PaymentStatusDomain;
import pl.training.payments.ports.output.TimeProvider;

@RequiredArgsConstructor
public class ProcessPaymentService {

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentsWriterAdapter paymentsWriter;
    private final TimeProvider timeProvider;

    public PaymentDomain process(PaymentRequestDomain paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);
        return paymentsWriter.save(payment);
    }

    private PaymentDomain createPayment(Money paymentValue) {
        return PaymentDomain.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimestamp())
                .status(PaymentStatusDomain.STARTED)
                .build();
    }

    private Money calculatePaymentValue(Money paymentValue) {
        var paymentFee = paymentFeeCalculator.calculateFee(paymentValue);
        return paymentValue.add(paymentFee);
    }

}
