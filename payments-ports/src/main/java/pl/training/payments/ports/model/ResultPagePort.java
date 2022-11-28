package pl.training.payments.ports.model;

import lombok.Value;

import java.util.List;

@Value
public class ResultPagePort<T> {

    List<T> data;
    int pageNumber;
    long totalPages;

}
