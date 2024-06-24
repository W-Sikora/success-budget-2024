package pl.wsikora.successbudget.v3.budget.domain;

import java.util.Arrays;
import java.util.List;


public enum BudgetComponents {

	SHOULD(true),
	COULD(true),
	WOULD(true),
	REPAYMENT(false),
	SAVINGS(true);

	private final boolean present;

	BudgetComponents(boolean present) {

		this.present = present;
	}

	public static List<BudgetComponents> getAlwaysPresent() {

		return Arrays.stream(values())
			.filter(value -> value.present)
			.toList();
	}
}
