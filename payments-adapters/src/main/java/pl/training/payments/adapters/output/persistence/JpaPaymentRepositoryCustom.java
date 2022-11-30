package pl.training.payments.adapters.output.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

public class JpaPaymentRepositoryCustom implements JpaPaymentRepositoryExtensions {

    @Setter
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PaymentEntity> getWithValueGreaterThan(BigDecimal value) {
        return entityManager.createNamedQuery(PaymentEntity.GET_WITH_VALUE_GREATER_THAN, PaymentEntity.class)
                .setParameter("value", value)
                .getResultList();
    }

}
