package pl.training.payments.domain.model;

import lombok.Value;

@Value
public class PageDomain {

    int number;
    int size;

    public int getOffset() {
        return number * size;
    }

}
