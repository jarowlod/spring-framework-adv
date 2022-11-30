package pl.training.payments.adapters.output.persistence.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.training.payments.adapters.output.persistence.jpa.JpaPaymentRepository;
import pl.training.payments.adapters.output.persistence.jpa.PaymentEntity;
import pl.training.payments.adapters.output.persistence.jpa.PropertyEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Transactional
@Component
@RequiredArgsConstructor
@Log
public class MongoExamples implements ApplicationRunner {

    private final MongoPaymentRepository paymentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var paymentDocument = initDatabase();

        paymentRepository.getById(paymentDocument.getId())
                .ifPresent(paymentProjectionView -> log.info("Payment view: " + paymentProjectionView.getDescription()));
    }

    private PaymentDocument initDatabase() {
        var payment = new PaymentDocument();
        payment.setId(UUID.randomUUID().toString());
        payment.setStatus("CONFIRMED");
        payment.setTimestamp(Instant.now());
        payment.setValue(BigDecimal.TEN);
        payment.setCurrency("PLN");
        return paymentRepository.save(payment);
    }

}
