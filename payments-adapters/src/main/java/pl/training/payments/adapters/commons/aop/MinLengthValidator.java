package pl.training.payments.adapters.commons.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static pl.training.payments.adapters.commons.Annotations.taskForArgument;

@Aspect
@Component
public class MinLengthValidator {

    @Before("execution(* *(@pl.training.payments.adapters.commons.aop.MinLength (*)))")
    public void validate(JoinPoint joinPoint) throws Throwable {
        taskForArgument(joinPoint, MinLength.class, (String argument, MinLength minLength) -> {
            if (argument.length() < minLength.value()) {
                throw new IllegalArgumentException("Value is too short, required length is: " + minLength.value());
            }
        });
    }

}

