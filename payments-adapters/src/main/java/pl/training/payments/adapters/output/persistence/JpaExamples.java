package pl.training.payments.adapters.output.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Transactional
@Component
@RequiredArgsConstructor
@Log
public class JpaExamples implements ApplicationRunner {

    private final JpaPaymentRepository paymentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var paymentEntity = initDatabase();
        var id= paymentEntity.getId();

        paymentRepository.getPaymentViewById(id)
                .ifPresent(paymentEntityView -> log.info("Payment view: " + paymentEntityView));

        paymentRepository.getPaymentProjectionById(id)
                .ifPresent(paymentEntityProjection -> log.info("Payment projection: " + paymentEntityProjection.getDescription()));

        paymentRepository.updatePaymentValues(BigDecimal.ONE);

        paymentRepository.getAll()
                .map(PaymentEntity::getStatus)
                .forEach(status -> log.info("Status: " + status));

        var result= paymentRepository.getAllAsync()
                .get(3, TimeUnit.SECONDS);
        log.info("Result: " + result);

        log.info(paymentRepository.getWithValueGreaterThan(BigDecimal.ONE).toString());
    }

    private PaymentEntity initDatabase() {
        var payment = new PaymentEntity();
        payment.setId(UUID.randomUUID().toString());
        payment.setStatus("CONFIRMED");
        payment.setTimestamp(Instant.now());
        payment.setValue(BigDecimal.TEN);
        payment.setCurrency("PLN");
        return paymentRepository.save(payment);
    }

}
