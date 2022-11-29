package pl.training.payments.adapters.commons.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static pl.training.payments.adapters.commons.Annotations.findAnnotation;
import static pl.training.payments.adapters.commons.Annotations.getTargetMethod;

@Aspect
@Component
public class MinLengthValidator {

    @Before("execution(* *(@pl.training.payments.adapters.commons.aop.MinLength (*)))")
    public void validate(JoinPoint joinPoint) throws Throwable {
        var arguments = joinPoint.getArgs();
        var argumentsAnnotations = getTargetMethod(joinPoint).getParameterAnnotations();
        for (int index = 0; index < arguments.length; index++) {
            var argument = (String) arguments[index];
            findAnnotation(argumentsAnnotations[index], MinLength.class)
                    .ifPresent(minLength -> validate(argument, minLength));
        }
    }

    private void validate(String argument, MinLength minLength) {
        if (argument.length() < minLength.value()) {
            throw  new IllegalArgumentException("Value is too short, required length is: " + minLength.value());
        }
    }

}
