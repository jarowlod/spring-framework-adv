package pl.training.payments.adapters.output.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;
import static pl.training.payments.adapters.output.persistence.jpa.PaymentEntity.WITHOUT_PROPERTIES;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, String>, JpaPaymentRepositoryExtensions, JpaSpecificationExecutor<PaymentEntity> {

    Page<PaymentEntity> getByStatus(String status, Pageable pageable);

    @Query("select p from Payment p where p.status = 'COMPLETED' and p.value >= :value")
    Page<PaymentEntity> getCompletedWithValue(/*@Param("value")*/ BigDecimal value, Pageable pageable);

    @Query("select p from Payment as p where p.status = 'STARTED'")
    List<PaymentEntity> getAllStarted();

    @Query("select new pl.training.payments.adapters.output.persistence.jpa.PaymentEntityView(p.id, p.status) from Payment p where p.id = :id")
    Optional<PaymentEntityView> getPaymentViewById(String id);

    @Query("select p.id as id, p.status as status from Payment p where p.id = :id")
    Optional<PaymentEntityProjection> getPaymentProjectionById(String id);

    @Transactional
    @Modifying
    @Query("update Payment p set p.value = p.value + :amount")
    void updatePaymentValues(BigDecimal amount);

    @Query("select p from Payment p")
    Stream<PaymentEntity> getAll();

    @Async
    @Query("select p from Payment p")
    Future<List<PaymentEntity>> getAllAsync();

    // LOAD - All attributes specified in entity graph will be treated as Eager, and all attribute not specified will be treated as Lazy
    // Fetch - All attributes specified in entity graph will be treated as Eager, and all attribute not specified use their default/mapped value
    @EntityGraph(value = WITHOUT_PROPERTIES, type = FETCH)
    //@EntityGraph(attributePaths = "properties", type = FETCH)
    @Query("select p from Payment p where p.id = :id")
    Optional<PaymentEntity> loadById(String id);

}
