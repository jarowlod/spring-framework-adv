package pl.training.payments.adapters.commons.aop;

import lombok.extern.java.Log;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Log
public class ExecutionLogger implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("Executing method: " + invocation.getMethod());
        return invocation.proceed();
    }

}

