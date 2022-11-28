package pl.training.payments.adapters.input.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.payments.adapters.commons.data.MoneyMapper;
import pl.training.payments.adapters.commons.web.ResultPageDto;
import pl.training.payments.ports.model.*;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = MoneyMapper.class)
public interface RestPaymentMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequestPort toPort(PaymentRequestDto paymentRequestDto);

    PaymentDto toDto(PaymentPort paymentPort);

    ResultPageDto<PaymentDto> toDto(ResultPagePort<PaymentPort> paymentPortResultPage);

    default String toDto(PaymentIdPort paymentIdPort) {
        return paymentIdPort.getValue();
    }

    default PaymentIdPort toPort(String idDto) {
        return new PaymentIdPort(idDto);
    }

    default PaymentRequestIdPort toPort(Long idDto) {
        return new PaymentRequestIdPort(idDto);
    }

    default MoneyPort valueToPort(String valueDto) {
        var parts = valueDto.split("\\s");
        return new MoneyPort(new BigDecimal(parts[0]), parts[1]);
    }

    default String toDto(MoneyPort moneyPort) {
        return moneyPort.getValue() + " " + moneyPort.getCurrencySymbol();
    }

}
