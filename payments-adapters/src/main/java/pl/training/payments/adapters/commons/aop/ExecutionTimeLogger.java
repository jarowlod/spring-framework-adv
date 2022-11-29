package pl.training.payments.adapters.commons.aop;

import lombok.Setter;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import pl.training.commons.aop.ExecutionTime;

import static pl.training.commons.aop.ExecutionTime.TimeUnit.NS;

//@Order(1_000)
@Aspect
@Component
@Log
public class ExecutionTimeLogger implements Ordered {

    @Value("${timeExecutionLoggerOrder}")
    @Setter
    private int order;

    @Around("@annotation(executionTime)")
    public Object log(ProceedingJoinPoint joinPoint, ExecutionTime executionTime) throws Throwable {
        var timeUnit = executionTime.timeUnit();
        var startTime = getTime(timeUnit);
        var result = joinPoint.proceed();
        var endTime = getTime(timeUnit);
        var totalTime = endTime - startTime;
        log.info("Method %s executed in %d %s".formatted(joinPoint.getSignature(), totalTime, timeUnit.name().toLowerCase()));
        return result;
    }

    private long getTime(ExecutionTime.TimeUnit timeUnit) {
        return timeUnit == NS ? System.nanoTime() : System.currentTimeMillis();
    }

    @Override
    public int getOrder() {
        return order;
    }

}
