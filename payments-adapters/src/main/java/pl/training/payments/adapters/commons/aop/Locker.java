package pl.training.payments.adapters.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pl.training.commons.aop.Lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static pl.training.commons.aop.Lock.LockType.WRITE;

@Aspect
@Component
public class Locker {

    @Around("@annotation(lock)")
    public Object lock(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        var newLock = new ReentrantReadWriteLock();
        var targetLock= lock.type() == WRITE ? newLock.writeLock() : newLock.readLock();
        targetLock.lock();
        try {
            return joinPoint.proceed();
        } finally {
            targetLock.unlock();
        }
    }

}
