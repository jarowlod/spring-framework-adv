package pl.training.payments.adapters.input.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.training.payments.adapters.commons.data.MoneyMapper;
import pl.training.payments.adapters.commons.web.ResultPageDto;
import pl.training.payments.ports.model.PaymentIdPort;
import pl.training.payments.ports.model.PaymentPort;
import pl.training.payments.ports.model.PaymentRequestPort;
import pl.training.payments.ports.model.ResultPagePort;

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

}
