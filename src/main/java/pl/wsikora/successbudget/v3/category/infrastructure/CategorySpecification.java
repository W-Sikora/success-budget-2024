package pl.wsikora.successbudget.v3.category.infrastructure;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pl.wsikora.successbudget.v3.category.application.query.CategoryFilterAttributes;
import pl.wsikora.successbudget.v3.category.domain.Category;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractSpecification;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;


class CategorySpecification extends AbstractSpecification<Category, CategoryFilterAttributes> {

	@Override
	protected Specification<Category> createSpecification(CategoryFilterAttributes categoryFilterAttributes) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (hasText(categoryFilterAttributes.getName())) {

				predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name").get("name")),
						"%" + categoryFilterAttributes.getName().toLowerCase() + "%"));
			}

			if (nonNull(categoryFilterAttributes.getPriority())) {

				predicates.add(criteriaBuilder.equal(root.get("priority"), Priority.of(categoryFilterAttributes.getPriority())));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	@Override
	protected Specification<Category> hasAccess(UserId userId) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
	}
}
