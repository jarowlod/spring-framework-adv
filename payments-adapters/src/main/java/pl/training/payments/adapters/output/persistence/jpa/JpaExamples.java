package pl.training.payments.adapters.output.persistence.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.training.payments.adapters.commons.data.SearchCriteria;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static pl.training.payments.adapters.commons.data.SearchCriteria.Operator.EQUAL;
import static pl.training.payments.adapters.commons.data.SearchCriteria.Operator.START_WITH;

@Transactional
@Component
@RequiredArgsConstructor
@Log
public class JpaExamples implements ApplicationRunner {

    private final JpaPaymentRepository paymentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //var paymentEntity = initDatabase();
        var id ="3cba6ab7-8c28-46c5-81d5-5ee6e37cb21f";

        /*paymentRepository.getPaymentViewById(id)
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

        var exampleEntity = new PaymentEntity();
        exampleEntity.setStatus("CONFIRMED");
        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues();
        var secondResult = paymentRepository.findAll(Example.of(exampleEntity, matcher), Sort.by(ASC, "timestamp"));
        log.info("Result: " + secondResult);*/

        //paymentRepository.findById(id);
        //paymentRepository.findAll();
        //paymentRepository.loadById(id);

        /*var specification = new PaymentsSpecification(Set.of(
                new SearchCriteria("status", START_WITH, "CON"),
                new SearchCriteria("value", EQUAL, BigDecimal.TEN)
        ));

        log.info(paymentRepository.findAll(specification).toString());*/
    }

    private PaymentEntity initDatabase() {
        var property = new PropertyEntity();
        property.setKey("cardNumber");
        property.setValue("1234567890");

        var secondProperty = new PropertyEntity();
        secondProperty.setKey("cvv");
        secondProperty.setValue("123");

        var payment = new PaymentEntity();
        payment.setId(UUID.randomUUID().toString());
        payment.setStatus("CONFIRMED");
        payment.setTimestamp(Instant.now());
        payment.setValue(BigDecimal.TEN);
        payment.setCurrency("PLN");
        payment.setProperties(List.of(property, secondProperty));
        return paymentRepository.save(payment);
    }

}
