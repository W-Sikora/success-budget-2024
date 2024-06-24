package pl.wsikora.successbudget.v3.liability.domain;


import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.type.description.Description;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Objects;


@Entity
@Table(name = "liabilities")
@Access(AccessType.FIELD)
public class Liability extends AbstractEntityWithUserId {

	public static final int LIMIT_PER_USER = 10;

	@Embedded
	private Name name;

	@Embedded
	private Description description;

	@Embedded
	@AttributeOverride(name = "name", column = @Column(name = "creditor"))
	private Name creditor;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "currency", column = @Column(name = "total_currency")),
			@AttributeOverride(name = "amount", column = @Column(name = "total_amount"))
	})
	private Money total;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "currency", column = @Column(name = "minimum_monthly_currency")),
			@AttributeOverride(name = "amount", column = @Column(name = "minimum_monthly_amount"))
	})
	private Money minimumMonthly;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "currency", column = @Column(name = "already_repaid_currency")),
			@AttributeOverride(name = "amount", column = @Column(name = "already_repaid_amount"))
	})
	private Money alreadyRepaid;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "currency", column = @Column(name = "repaid_currency")),
			@AttributeOverride(name = "amount", column = @Column(name = "repaid_amount"))
	})
	private Money repaid;


	@Column(name = "paid_off", nullable = false)
	private boolean paidOff;

	protected Liability() {}

	public Liability(UserId userId, Name name, Description description, Name creditor,
					 Money total, Money minimumMonthly, Money alreadyRepaid) {

		super(userId);

		Assert.notNull(name, "name must not be null");
		Assert.notNull(description, "description must not be null");
		Assert.notNull(creditor, "creditor must not be null");
		Assert.notNull(total, "totalAmount must not be null");
		Assert.notNull(minimumMonthly, "minimumMonthlyAmount must not be null");
		Assert.notNull(alreadyRepaid, "alreadyRepaid must not be null");

		this.name = name;
		this.description = description;
		this.creditor = creditor;
		this.total = total;
		this.minimumMonthly = minimumMonthly;
		this.alreadyRepaid = alreadyRepaid;
		this.repaid = Money.of(total.getCurrency());
	}

	public Name getName() {

		return name;
	}

	public void updateName(Name name) {

		Assert.notNull(name, "name must not be null");

		this.name = name;
	}

	public Description getDescription() {

		return description;
	}

	public void updateDescription(Description description) {

		Assert.notNull(description, "description must not be null");

		this.description = description;
	}

	public Name getCreditor() {

		return creditor;
	}

	public void updateCreditor(Name creditor) {

		Assert.notNull(creditor, "creditor must not be null");

		this.creditor = creditor;
	}

	public Money getTotal() {

		return total;
	}

	public void updateTotal(Money total) {

		this.total = total;
	}

	public Money getMinimumMonthly() {

		return minimumMonthly;
	}

	public void updateMinimumMonthly(Money minimumMonthly) {

		this.minimumMonthly = minimumMonthly;
	}

	public Money getAlreadyRepaid() {

		return alreadyRepaid;
	}

	public void updateAlreadyRepaid(Money alreadyRepaid) {

		this.alreadyRepaid = alreadyRepaid;
	}

	public Money getRepaid() {

		return repaid;
	}

	public void addMoney(Money money) {

		Assert.notNull(money, "money must not be null");

		this.repaid = repaid.add(money);
	}

	public Money getTotalRepaid() {

		return alreadyRepaid.add(repaid);
	}

	public Money getRemainingToBeRepaid() {

		return total.subtract(getTotalRepaid());
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof Liability liability)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return Objects.equals(name, liability.name)
				&& Objects.equals(description, liability.description)
				&& Objects.equals(creditor, liability.creditor)
				&& Objects.equals(total, liability.total)
				&& Objects.equals(minimumMonthly, liability.minimumMonthly)
				&& Objects.equals(repaid, liability.repaid);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), name, description, creditor, total, minimumMonthly, repaid);
	}
}
