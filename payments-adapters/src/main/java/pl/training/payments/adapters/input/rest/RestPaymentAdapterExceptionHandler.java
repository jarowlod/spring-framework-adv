package pl.training.payments.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.training.payments.adapters.commons.web.ExceptionDto;
import pl.training.payments.ports.model.PaymentNotFoundException;
import pl.training.payments.adapters.commons.web.RestExceptionResponseBuilder;

import java.util.Locale;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(HIGHEST_PRECEDENCE)
@ControllerAdvice("pl.training.payments.adapters.input.rest")
@RequiredArgsConstructor
public class RestPaymentAdapterExceptionHandler {

    private final RestExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFoundException(PaymentNotFoundException paymentNotFoundException, Locale locale) {
        return responseBuilder.build(paymentNotFoundException, NOT_FOUND, locale);
    }

}
