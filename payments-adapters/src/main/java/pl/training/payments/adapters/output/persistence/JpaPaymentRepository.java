package pl.training.payments.adapters.output.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, String>, JpaPaymentRepositoryExtensions {

    Page<PaymentEntity> getByStatus(String status, Pageable pageable);

    @Query("select p from Payment p where p.status = 'COMPLETED' and p.value >= :value")
    Page<PaymentEntity> getCompletedWithValue(/*@Param("value")*/ BigDecimal value, Pageable pageable);

    @Query("select p from Payment as p where p.status = 'STARTED'")
    List<PaymentEntity> getAllStarted();

    @Query("select new pl.training.payments.adapters.output.persistence.PaymentEntityView(p.id, p.status) from Payment p where p.id = :id")
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

}
