package pl.training.payments.adapters.output.persistence;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@NamedEntityGraph(name = PaymentEntity.WITH_PROPERTIES, attributeNodes = @NamedAttributeNode("properties"))
@NamedEntityGraph(name = PaymentEntity.WITHOUT_PROPERTIES)
@NamedQuery(name = PaymentEntity.GET_WITH_VALUE_GREATER_THAN, query = "select p from Payment p where p.value > :value")
@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PaymentEntity {

    public static final String GET_WITH_VALUE_GREATER_THAN = "paymentsByValueGreaterThan";
    public static final String WITH_PROPERTIES = "paymentsWithProperties";
    public static final String WITHOUT_PROPERTIES = "paymentsWithoutProperties";

    @Id
    private String id;
    @Column(name = "amount")
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;
    @JoinColumn
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PropertyEntity> properties;

}
