package pl.training.payments.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.payments.adapters.commons.web.ResultPageDto;
import pl.training.payments.ports.input.GetPaymentUseCase;
import pl.training.payments.ports.model.PagePort;

import static pl.training.payments.ports.model.PaymentStatusPort.STARTED;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class RestGetPaymentUseCaseAdapter {

    private final GetPaymentUseCase getPaymentUseCase;
    private final RestPaymentMapper mapper;

    @GetMapping("{idDto}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String idDto) {
        var paymentIdPort = mapper.toPort(idDto);
        var paymentPort = getPaymentUseCase.getById(paymentIdPort);
        var paymentDto = mapper.toDto(paymentPort);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("started")
    public ResponseEntity<ResultPageDto<PaymentDto>> getStartedPayments(
            @RequestParam(required = false, defaultValue = "0") int pageNumer,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        var resultPagePort = getPaymentUseCase.getByStatus(STARTED, new PagePort(pageNumer, pageSize));
        var resultPageDto = mapper.toDto(resultPagePort);
        return ResponseEntity.ok(resultPageDto);
    }

}
