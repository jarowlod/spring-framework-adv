package pl.training.payments.adapters.input.rest;

import lombok.Data;

import jakarta.validation.constraints.Min;
import pl.training.payments.adapters.commons.data.validation.Base;
import pl.training.payments.adapters.commons.data.validation.Extended;
import pl.training.payments.adapters.commons.data.validation.Money;

@Data
public class PaymentRequestDto {

    @Min(value = 1, groups = Extended.class)
    private Long requestId;
    /*@Pattern(regexp = "\\d+ [A-Z]+")
    @NotNull*/
    @Money(groups = {Base.class, Extended.class})
    private String value;

}
