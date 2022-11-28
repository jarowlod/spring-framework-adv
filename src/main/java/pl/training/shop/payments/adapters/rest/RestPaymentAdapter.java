package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.commons.data.Page;
import pl.training.shop.commons.data.validation.Base;
import pl.training.shop.commons.web.LocationUri;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.payments.ports.PaymentService;

import static pl.training.shop.payments.domain.PaymentStatusDomain.STARTED;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class RestPaymentAdapter {

    private final PaymentService paymentService;
    private final RestPaymentMapper mapper;

    @PostMapping()
    public ResponseEntity<PaymentDto> process(/*@Valid*/ @Validated(Base.class) @RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequestDomain = mapper.toDomain(paymentRequestDto);
        var paymentDomain = paymentService.process(paymentRequestDomain);
        var paymentDto = mapper.toDto(paymentDomain);
        var locationUri = LocationUri.fromRequest(paymentDto.getId());
        return ResponseEntity.created(locationUri)
                .body(paymentDto);
    }

    @GetMapping("{idDto}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String idDto) {
        var paymentIdDomain = mapper.toDomain(idDto);
        var paymentDomain = paymentService.getById(paymentIdDomain);
        var paymentDto = mapper.toDto(paymentDomain);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("started")
    public ResponseEntity<ResultPageDto<PaymentDto>> getStartedPayments(
            @RequestParam(required = false, defaultValue = "0") int pageNumer,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        var resultPage = paymentService.getByStatus(STARTED, new Page(pageNumer, pageSize));
        var resultPageDto = mapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

    /*@ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFoundException(PaymentNotFoundException paymentNotFoundException) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ExceptionDto(Instant.now(), "Payment not found"));
    }*/

}
