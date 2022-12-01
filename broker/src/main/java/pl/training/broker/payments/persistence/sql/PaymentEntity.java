package pl.training.broker.payments.persistence.sql;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "payments")
@Data
public class PaymentEntity {

    @Id
    private Long id;
    @Column("amount")
    private String value;
    private String status;

}
