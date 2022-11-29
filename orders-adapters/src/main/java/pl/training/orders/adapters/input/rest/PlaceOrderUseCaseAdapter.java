package pl.training.orders.adapters.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.orders.ports.input.PlaceOrderUseCase;

@RequestMapping("api/orders")
@RestController
@RequiredArgsConstructor
public class PlaceOrderUseCaseAdapter {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final RestOrderMapper mapper;

    @PostMapping
    public ResponseEntity<Void> place(@RequestBody OrderDto orderDto) {
        var orderPort = mapper.toPort(orderDto);
        placeOrderUseCase.place(orderPort);
        return ResponseEntity.accepted().build();
    }

}
