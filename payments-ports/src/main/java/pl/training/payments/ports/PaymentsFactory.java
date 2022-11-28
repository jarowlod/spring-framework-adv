package pl.training.payments.ports;

import pl.training.payments.ports.input.GetPaymentUseCase;
import pl.training.payments.ports.input.ProcessPaymentUseCase;
import pl.training.payments.ports.output.PaymentsReader;
import pl.training.payments.ports.output.PaymentsWriter;
import pl.training.payments.ports.output.TimeProvider;

public interface PaymentsFactory {

    GetPaymentUseCase getPaymentUseCase(PaymentsReader paymentsReader);

    ProcessPaymentUseCase processPaymentUseCase(PaymentsWriter paymentsWriter, TimeProvider timeProvider);

}
