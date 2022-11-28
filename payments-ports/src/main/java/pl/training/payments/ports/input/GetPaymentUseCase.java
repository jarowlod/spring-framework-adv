package pl.training.payments.ports.input;

import pl.training.payments.ports.model.*;

public interface GetPaymentUseCase {

    PaymentPort getById(PaymentIdPort paymentIdPort);

    ResultPagePort<PaymentPort> getByStatus(PaymentStatusPort paymentStatusPort, PagePort pagePort);

}
