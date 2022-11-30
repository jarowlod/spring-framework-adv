package pl.training.payments.adapters.commons.data;

import lombok.Value;

@Value
public class SearchCriteria {

    String propertyName;
    Operator operator;
    Object value;

    public enum Operator {

        EQUAL, NOT_EQUAL, START_WITH

    }

}
