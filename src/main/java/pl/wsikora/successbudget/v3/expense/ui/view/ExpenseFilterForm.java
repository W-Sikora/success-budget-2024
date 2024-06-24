package pl.wsikora.successbudget.v3.expense.ui.view;

import pl.wsikora.successbudget.v3.expense.application.query.ExpenseFilterAttributes;


public class ExpenseFilterForm implements ExpenseFilterAttributes {

	private String name;
	private Long categoryId;
	private String dateFrom;
	private String dateTo;

	public ExpenseFilterForm() {}

	@Override
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public Long getCategoryId() {

		return categoryId;
	}

	public void setCategoryId(Long categoryId) {

		this.categoryId = categoryId;
	}

	@Override
	public String getDateFrom() {

		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {

		this.dateFrom = dateFrom;
	}

	@Override
	public String getDateTo() {

		return dateTo;
	}

	public void setDateTo(String dateTo) {

		this.dateTo = dateTo;
	}
}
