package pl.training.payments.adapters.commons.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import pl.training.commons.aop.Atomic;

import static pl.training.payments.adapters.commons.Annotations.findAnnotation;

@Aspect
@RequiredArgsConstructor
public class TransactionWrapper {

    private final PlatformTransactionManager transactionManager;

    @Around("@annotation(pl.training.commons.aop.Atomic) || within(@pl.training.commons.aop.Atomic *)")
    public Object runWithTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        var annotation = findAnnotation(joinPoint, Atomic.class);
        var transactionDefinition = transactionDefinition(annotation);
        var transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            var result = joinPoint.proceed();
            transactionManager.commit(transactionStatus);
            return result;
        } catch (Throwable throwable) {
            transactionStatus.setRollbackOnly();
            throw throwable;
        }
    }

    private TransactionDefinition transactionDefinition(Atomic atomic) {
        var transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(atomic.timeoutInMilliseconds());
        return transactionDefinition;
    }

}
