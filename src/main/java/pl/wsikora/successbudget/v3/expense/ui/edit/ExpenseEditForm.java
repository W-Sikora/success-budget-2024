package pl.wsikora.successbudget.v3.expense.ui.edit;

import pl.wsikora.successbudget.v3.expense.application.command.ExpenseAttributes;

import java.math.BigDecimal;


public class ExpenseEditForm implements ExpenseAttributes {

	static final String F_NAME = "name";
	static final String F_DESCRIPTION = "description";
	static final String F_DATE = "date";
	static final String F_AMOUNT = "amount";
	static final String F_CURRENCY = "currency";
	static final String F_CATEGORY_ID = "categoryId";

	private Long id;
	private Long userId;
	private String name;
	private String description;
	private String date;
	private BigDecimal amount;
	private Integer currency;
	private Long categoryId;

	public ExpenseEditForm(Long userId) {

		this.userId = userId;
	}

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
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	@Override
	public String getDate() {

		return date;
	}

	public void setDate(String date) {

		this.date = date;
	}

	@Override
	public BigDecimal getAmount() {

		return amount;
	}

	public void setAmount(BigDecimal amount) {

		this.amount = amount;
	}

	@Override
	public Integer getCurrency() {

		return currency;
	}

	public void setCurrency(Integer currency) {

		this.currency = currency;
	}

	@Override
	public Long getCategoryId() {

		return categoryId;
	}

	public void setCategoryId(Long categoryId) {

		this.categoryId = categoryId;
	}
}
