package pl.wsikora.successbudget.v3.expense.infrastructure;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractSpecification;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.expense.application.query.ExpenseFilterAttributes;
import pl.wsikora.successbudget.v3.expense.domain.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;
import static pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId.F_CATEGORY_ID;
import static pl.wsikora.successbudget.v3.common.type.date.Date.F_DATE;
import static pl.wsikora.successbudget.v3.common.type.name.Name.F_NAME;


class ExpenseSpecification extends AbstractSpecification<Expense, ExpenseFilterAttributes> {

	@Override
	protected Specification<Expense> createSpecification(ExpenseFilterAttributes expenseFilterAttributes) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			String name = expenseFilterAttributes.getName();

			if (hasText(name)) {

				predicates.add(criteriaBuilder.like(root.get(F_NAME).get(F_NAME), "%" + name + "%"));
			}

			Long categoryId = expenseFilterAttributes.getCategoryId();

			if (nonNull(categoryId)) {

				predicates.add(criteriaBuilder.equal(root.get(F_CATEGORY_ID).get(F_CATEGORY_ID), categoryId));
			}

			String dateFrom = expenseFilterAttributes.getDateFrom();

			if (hasText(dateFrom)) {

				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(F_DATE).get(F_DATE), LocalDate.parse(dateFrom)));
			}

			String dateTo = expenseFilterAttributes.getDateTo();

			if (hasText(dateTo)) {

				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(F_DATE).get(F_DATE), LocalDate.parse(dateTo)));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}

	@Override
	protected Specification<Expense> hasAccess(UserId userId) {

		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
	}
}
