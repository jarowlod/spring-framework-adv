package pl.training.payments.adapters.output.persistence;

import java.math.BigDecimal;
import java.util.List;

public interface JpaPaymentRepositoryExtensions {

    List<PaymentEntity> getWithValueGreaterThan(BigDecimal value);

}
