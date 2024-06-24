package pl.wsikora.successbudget.v3.income.ui.edit;

import pl.wsikora.successbudget.v3.income.application.command.IncomeAttributes;

import java.math.BigDecimal;


public class IncomeEditForm implements IncomeAttributes {

	static final String F_INITIAL_CURRENCY = "initialCurrency";
	static final String F_INITIAL_AMOUNT = "initialAmount";

	private Long id;
	private Long userId;
	private Integer initialCurrency;
	private BigDecimal initialAmount;

	public IncomeEditForm() {}

	@Override
	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	@Override
	public Long getUserId() {

		return userId;
	}

	public void setUserId(Long userId) {

		this.userId = userId;
	}

	@Override
	public Integer getInitialCurrency() {

		return initialCurrency;
	}

	public void setInitialCurrency(Integer initialCurrency) {

		this.initialCurrency = initialCurrency;
	}

	@Override
	public BigDecimal getInitialAmount() {

		return initialAmount;
	}

	public void setInitialAmount(BigDecimal initialAmount) {

		this.initialAmount = initialAmount;
	}
}
