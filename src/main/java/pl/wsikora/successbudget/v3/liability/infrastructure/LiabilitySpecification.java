package pl.wsikora.successbudget.v3.liability.infrastructure;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractSpecification;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityFilterAttributes;
import pl.wsikora.successbudget.v3.liability.domain.Liability;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;


class LiabilitySpecification extends AbstractSpecification<Liability, LiabilityFilterAttributes> {

	@Override
	protected Specification<Liability> createSpecification(LiabilityFilterAttributes liabilityFilterAttributes) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (hasText(liabilityFilterAttributes.getName())) {

				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name").get("name")),
						"%" + liabilityFilterAttributes.getName().toLowerCase() + "%"));
			}

			predicates.add(criteriaBuilder.isFalse(root.get("paidOff")));

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	@Override
	protected Specification<Liability> hasAccess(UserId userId) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
	}
}
