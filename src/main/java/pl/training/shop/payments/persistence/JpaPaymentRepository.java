package pl.training.shop.payments.persistence;

import lombok.Setter;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaPaymentRepository {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    public PaymentEntity save(PaymentEntity paymentEntity) {
        entityManager.persist(paymentEntity);
        return paymentEntity;
    }

    public Optional<PaymentEntity> getById(String idEntity) {
        return Optional.ofNullable(entityManager.find(PaymentEntity.class, idEntity));
    }

}
