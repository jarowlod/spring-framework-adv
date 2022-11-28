package pl.training.payments.ports.output;

import pl.training.payments.ports.model.*;

import java.util.Optional;

public interface PaymentsReader {

    Optional<PaymentPort> getById(PaymentIdPort paymentIdPort);

    ResultPagePort<PaymentPort> getByStatus(PaymentStatusPort paymentStatusPort, PagePort pagePort);

}
