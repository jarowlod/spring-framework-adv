package pl.training.payments.ports.output;

import pl.training.payments.ports.model.PaymentPort;

public interface PaymentsWriter {

    PaymentPort persist(PaymentPort paymentPort);

}
