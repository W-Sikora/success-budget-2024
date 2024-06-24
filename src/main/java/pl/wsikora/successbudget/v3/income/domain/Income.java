package pl.wsikora.successbudget.v3.income.domain;


import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Objects;


@Entity
@Table(name = "incomes")
@Access(AccessType.FIELD)
public class Income extends AbstractEntityWithUserId {

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "currency", column = @Column(name = "initial_currency")),
			@AttributeOverride(name = "amount", column = @Column(name = "initial_amount")),
	})
	private Money initial;

	protected Income() {}

	public Income(UserId userId, Money initial) {

		super(userId);

		Assert.notNull(initial, "initial must not be null");

		this.initial = initial;
	}

	public Money getInitial() {

		return initial;
	}

	public void updateInitial(Money initial) {

		Assert.notNull(initial, "initial must not be null");

		this.initial = initial;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof Income income)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return Objects.equals(initial, income.initial);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), initial);
	}
}
