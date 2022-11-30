package pl.training.payments.adapters.output.persistence.jpa;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.training.payments.adapters.commons.data.SearchCriteria;

import java.util.Set;

@RequiredArgsConstructor
public class PaymentsSpecification implements Specification<PaymentEntity> {

    private final Set<SearchCriteria> searchCriteria;

    @Override
    public Predicate toPredicate(Root<PaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(searchCriteria.stream().map(criteria -> map(criteria, root, criteriaBuilder)).toArray(Predicate[]::new));
    }

    private Predicate map(SearchCriteria criteria, Root<PaymentEntity> root, CriteriaBuilder builder) {
        var property= criteria.getPropertyName();
        var value = criteria.getValue();
        return switch (criteria.getOperator()) {
            case EQUAL -> builder.equal(root.get(property), value);
            case NOT_EQUAL -> builder.notEqual(root.get(property), value);
            case START_WITH -> builder.like(root.get(property), value + "%");
        };
    }

}
