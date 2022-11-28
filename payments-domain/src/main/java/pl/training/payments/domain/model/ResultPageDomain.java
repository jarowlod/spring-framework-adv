package pl.training.payments.domain.model;

import lombok.Value;

import java.util.List;

@Value
public class ResultPageDomain<T> {

    List<T> data;
    int pageNumber;
    long totalPages;

}
