package pl.training.payments.adapters.commons.aop;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecutionInterceptor implements MethodInterceptor {

    private static final String BEFORE_PREFIX = "before";
    private static final String AFTER_RETURNING_PREFIX = "afterReturning";
    private static final String AFTER_THROWING_PREFIX = "afterThrowing";
    private static final String AFTER_PREFIX = "finally";

    private final Object aspect;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        findAndInvoke(BEFORE_PREFIX, Optional.empty());
        try {
            var result= invocation.proceed();
            findAndInvoke(AFTER_RETURNING_PREFIX, Optional.ofNullable(result));
            return result;
        } catch (Throwable throwable) {
            findAndInvoke(AFTER_THROWING_PREFIX, Optional.empty());
            throw throwable;
        } finally {
            findAndInvoke(AFTER_PREFIX, Optional.empty());
        }
    }

    private void findAndInvoke(String prefix, Optional<Object> payload) {
        Arrays.stream(aspect.getClass().getMethods())
                .filter(method -> method.getName().startsWith(prefix))
                .findFirst()
                .ifPresent(method -> invoke(method, payload));
    }

    @SneakyThrows
    private void invoke(Method method, Optional<Object> payload) {
        if (payload.isPresent()) {
            method.invoke(aspect, payload.get());
        } else {
            method.invoke(aspect);
        }
    }

}

