package pl.wsikora.successbudget.v3.budget.domain;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Objects;


@Entity
@Table(name = "budget_components_preferences")
@Access(AccessType.FIELD)
public class BudgetComponentsPreference extends AbstractEntityWithUserId {

	@Enumerated(EnumType.ORDINAL)
	private BudgetComponents budgetComponents;

	private Integer ranking;

	protected BudgetComponentsPreference() {}

	public BudgetComponentsPreference(UserId userId, BudgetComponents budgetComponents) {

		super(userId);

		Assert.notNull(budgetComponents, "budgetComponents must not be null");

		this.budgetComponents = budgetComponents;
	}

	public BudgetComponents getBudgetComponents() {

		return budgetComponents;
	}

	public Integer getRanking() {

		return ranking;
	}

	public void updateRanking(Integer ranking) {

		Assert.notNull(ranking, "ranking must not be null");

		this.ranking = ranking;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof BudgetComponentsPreference that)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return budgetComponents == that.budgetComponents
				&& Objects.equals(ranking, that.ranking);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), budgetComponents, ranking);
	}
}
