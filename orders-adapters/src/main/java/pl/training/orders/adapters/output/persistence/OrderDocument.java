package pl.training.orders.adapters.output.persistence;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

@Document
@Data
public class OrderDocument {

    @Id
    private String id;
    private List<OrderEntryDocument> entries;
    private String paymentStatus;

}
