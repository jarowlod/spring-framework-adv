package pl.training.payments.ports.input;

import pl.training.payments.ports.model.PaymentPort;
import pl.training.payments.ports.model.PaymentRequestPort;

public interface ProcessPaymentUseCase {

    PaymentPort process(PaymentRequestPort paymentRequestPort);

}
