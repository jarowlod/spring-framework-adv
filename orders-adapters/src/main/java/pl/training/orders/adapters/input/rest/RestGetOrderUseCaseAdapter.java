package pl.training.orders.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.orders.ports.input.GetOrderUseCase;
import pl.training.orders.ports.input.PlaceOrderUseCase;

@RequestMapping("api/orders")
@RestController
@RequiredArgsConstructor
public class RestGetOrderUseCaseAdapter {

    private final GetOrderUseCase getOrderUseCase;
    private final RestOrderMapper mapper;

    @GetMapping("{idDto}")
    public ResponseEntity<OrderDto> place(@PathVariable String idDto) {
        var idPort = mapper.toPort(idDto);
        var orderPort = getOrderUseCase.getById(idPort);
        return ResponseEntity.ok(mapper.toDto(orderPort));
    }

}
