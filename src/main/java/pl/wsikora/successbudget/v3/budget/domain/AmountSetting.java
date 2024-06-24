package pl.wsikora.successbudget.v3.budget.domain;


import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pl.wsikora.successbudget.v3.common.util.BigDecimalUtils.isGreaterThan;
import static pl.wsikora.successbudget.v3.common.util.BigDecimalUtils.isLessThan;


@Entity
@Table(name = "amount_settings")
@Access(AccessType.FIELD)
public class AmountSetting extends AbstractEntityWithUserId {

	@ElementCollection
	@CollectionTable(name = "minimum_allowable_must_expense_amounts")
	@Column(name = "amount")
	private List<BigDecimal> minimumAllowableMustExpenseAmounts;

	@ElementCollection
	@CollectionTable(name = "minimum_allowable_liability_amounts")
	@Column(name = "amount")
	private List<BigDecimal> minimumAllowableLiabilityAmounts;

	@ElementCollection
	@CollectionTable(name = "maximum_allowable_liability_amounts")
	@Column(name = "amount")
	private List<BigDecimal> maximumAllowableLiabilityAmounts;

	private BigDecimal maximumAllowableAmountOfShouldExpense;

	private BigDecimal maximumAllowableAmountOfCouldExpense;

	private BigDecimal maximumAllowableAmountOfWouldExpense;

	private BigDecimal disposableIncome;

	private Currency currency;

	protected AmountSetting() {}

	public AmountSetting(UserId userId, Currency currency) {

		super(userId);

		this.minimumAllowableMustExpenseAmounts = new ArrayList<>();
		this.minimumAllowableLiabilityAmounts = new ArrayList<>();
		this.maximumAllowableLiabilityAmounts = new ArrayList<>();
		this.maximumAllowableAmountOfShouldExpense = BigDecimal.ZERO;
		this.maximumAllowableAmountOfCouldExpense = BigDecimal.ZERO;
		this.maximumAllowableAmountOfWouldExpense = BigDecimal.ZERO;
		this.disposableIncome = BigDecimal.ZERO;
		this.currency = currency;
	}

	public List<BigDecimal> getMinimumAllowableMustExpenseAmounts() {

		return minimumAllowableMustExpenseAmounts;
	}

	public void updateMinimumAllowableMustExpenseAmounts(List<BigDecimal> minimumAllowableMustExpenseAmounts) {

		Assert.notNull(minimumAllowableMustExpenseAmounts, "minimumAllowableMustExpenseAmounts cannot be null");

		this.minimumAllowableMustExpenseAmounts = minimumAllowableMustExpenseAmounts;
	}

	public List<BigDecimal> getMinimumAllowableLiabilityAmounts() {

		return minimumAllowableLiabilityAmounts;
	}

	public void updateMinimumAllowableLiabilityAmounts(List<BigDecimal> minimumAllowableLiabilityAmounts) {

		Assert.notNull(minimumAllowableLiabilityAmounts, "minimumAllowableLiabilityAmounts cannot be null");

		this.minimumAllowableLiabilityAmounts = minimumAllowableLiabilityAmounts;
	}

	public List<BigDecimal> getMaximumAllowableLiabilityAmounts() {

		return maximumAllowableLiabilityAmounts;
	}

	public void updateMaximumAllowableLiabilityAmounts(List<BigDecimal> maximumAllowableLiabilityAmounts) {

		Assert.notNull(maximumAllowableLiabilityAmounts, "maximumAllowableLiabilityAmounts cannot be null");

		this.maximumAllowableLiabilityAmounts = maximumAllowableLiabilityAmounts;
	}

	public BigDecimal getMaximumAllowableAmountOfShouldExpense() {

		return maximumAllowableAmountOfShouldExpense;
	}

	public void updateMaximumAllowableAmountOfShouldExpense(BigDecimal minimumAllowableAmountOfShouldExpense) {

		Assert.notNull(minimumAllowableAmountOfShouldExpense, "maximumAllowableAmountOfShouldExpense cannot be null");

		this.maximumAllowableAmountOfShouldExpense = minimumAllowableAmountOfShouldExpense;
	}

	public BigDecimal getMaximumAllowableAmountOfCouldExpense() {

		return maximumAllowableAmountOfCouldExpense;
	}

	public void updateMaximumAllowableAmountOfCouldExpense(BigDecimal minimumAllowableAmountOfCouldExpense) {

		Assert.notNull(minimumAllowableAmountOfCouldExpense, "maximumAllowableAmountOfCouldExpense cannot be null");

		this.maximumAllowableAmountOfCouldExpense = minimumAllowableAmountOfCouldExpense;
	}

	public BigDecimal getMaximumAllowableAmountOfWouldExpense() {

		return maximumAllowableAmountOfWouldExpense;
	}

	public void updateMaximumAllowableAmountOfWouldExpense(BigDecimal minimumAllowableAmountOfWouldExpense) {

		Assert.notNull(minimumAllowableAmountOfWouldExpense, "maximumAllowableAmountOfWouldExpense cannot be null");

		this.maximumAllowableAmountOfWouldExpense = minimumAllowableAmountOfWouldExpense;
	}

	public BigDecimal getDisposableIncome() {

		return disposableIncome;
	}

	public void updateDisposableIncome(BigDecimal disposableIncome) {

		Assert.notNull(disposableIncome, "disposableIncome cannot be null");

		this.disposableIncome = disposableIncome;
	}

	public Currency getCurrency() {

		return currency;
	}

	public boolean isBalanced() {

		BigDecimal totalLiabilitiesAndExpenses = BigDecimal.ZERO;

		for (BigDecimal amount : minimumAllowableLiabilityAmounts) {

			totalLiabilitiesAndExpenses = totalLiabilitiesAndExpenses.add(amount);
		}

		for (BigDecimal amount : minimumAllowableMustExpenseAmounts) {

			totalLiabilitiesAndExpenses = totalLiabilitiesAndExpenses.add(amount);
		}

		totalLiabilitiesAndExpenses = totalLiabilitiesAndExpenses.add(maximumAllowableAmountOfShouldExpense);
		totalLiabilitiesAndExpenses = totalLiabilitiesAndExpenses.add(maximumAllowableAmountOfCouldExpense);
		totalLiabilitiesAndExpenses = totalLiabilitiesAndExpenses.add(maximumAllowableAmountOfWouldExpense);

		return isGreaterThan(disposableIncome, totalLiabilitiesAndExpenses);
	}

	public boolean areMinimumAllowableLiabilityAmountsTooHigh() {

		BigDecimal totalMinimumAllowableLiabilityAmount = BigDecimal.ZERO;

		for (BigDecimal amount : minimumAllowableLiabilityAmounts) {

			totalMinimumAllowableLiabilityAmount = totalMinimumAllowableLiabilityAmount.add(amount);
		}

		return isLessThan(disposableIncome, totalMinimumAllowableLiabilityAmount);
	}

	public boolean areMinimumAllowableMustExpenseAmountsTooHigh() {

		BigDecimal totalMinimumAllowableMustExpenseAmount = BigDecimal.ZERO;

		for (BigDecimal amount : minimumAllowableMustExpenseAmounts) {

			totalMinimumAllowableMustExpenseAmount = totalMinimumAllowableMustExpenseAmount.add(amount);
		}

		return isLessThan(disposableIncome, totalMinimumAllowableMustExpenseAmount);
	}

	public boolean areMaximumAllowableAmountOfShouldExpenseTooHigh() {

		return isLessThan(disposableIncome, maximumAllowableAmountOfShouldExpense);
	}

	public boolean areMaximumAllowableAmountOfCouldExpenseTooHigh() {

		return isLessThan(disposableIncome, maximumAllowableAmountOfCouldExpense);
	}

	public boolean areMaximumAllowableAmountOfWouldExpenseTooHigh() {

		return isLessThan(disposableIncome, maximumAllowableAmountOfWouldExpense);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}
		if (!(o instanceof AmountSetting that)) {

			return false;
		}
		if (!super.equals(o)) {

			return false;
		}

		return Objects.equals(minimumAllowableMustExpenseAmounts, that.minimumAllowableMustExpenseAmounts)
			&& Objects.equals(minimumAllowableLiabilityAmounts, that.minimumAllowableLiabilityAmounts)
			&& Objects.equals(maximumAllowableLiabilityAmounts, that.maximumAllowableLiabilityAmounts)
			&& Objects.equals(maximumAllowableAmountOfShouldExpense, that.maximumAllowableAmountOfShouldExpense)
			&& Objects.equals(maximumAllowableAmountOfCouldExpense, that.maximumAllowableAmountOfCouldExpense)
			&& Objects.equals(maximumAllowableAmountOfWouldExpense, that.maximumAllowableAmountOfWouldExpense)
			&& Objects.equals(disposableIncome, that.disposableIncome);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), minimumAllowableMustExpenseAmounts, minimumAllowableLiabilityAmounts,
			maximumAllowableLiabilityAmounts, maximumAllowableAmountOfShouldExpense, maximumAllowableAmountOfCouldExpense,
			maximumAllowableAmountOfWouldExpense, disposableIncome);
	}
}
