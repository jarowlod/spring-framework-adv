package pl.training.payments.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.payments.adapters.commons.data.validation.Base;
import pl.training.payments.adapters.commons.web.LocationUri;
import pl.training.payments.ports.input.ProcessPaymentUseCase;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class RestProcessPaymentUseCaseAdapter {

    private final ProcessPaymentUseCase processPaymentUseCase;
    private final RestPaymentMapper mapper;

    @PostMapping()
    public ResponseEntity<PaymentDto> process(/*@Valid*/ @Validated(Base.class) @RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequestPort = mapper.toPort(paymentRequestDto);
        var paymentPort = processPaymentUseCase.process(paymentRequestPort);
        var paymentDto = mapper.toDto(paymentPort);
        var locationUri = LocationUri.fromRequest(paymentDto.getId());
        return ResponseEntity.created(locationUri)
                .body(paymentDto);
    }

}
