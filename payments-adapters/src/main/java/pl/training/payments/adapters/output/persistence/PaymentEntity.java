package pl.training.payments.adapters.output.persistence;

import jakarta.persistence.NamedQuery;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@NamedQuery(name = PaymentEntity.GET_WITH_VALUE_GREATER_THAN, query = "select p from Payment p where p.value > :value")
@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PaymentEntity {

    public static final String GET_WITH_VALUE_GREATER_THAN = "paymentsByValueGreaterThan";

    @Id
    private String id;
    @Column(name = "amount")
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;


}
