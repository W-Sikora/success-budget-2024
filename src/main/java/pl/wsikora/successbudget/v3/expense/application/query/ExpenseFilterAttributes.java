package pl.wsikora.successbudget.v3.expense.application.query;


public interface ExpenseFilterAttributes {

	String getName();

	Long getCategoryId();

	String getDateFrom();

	String getDateTo();
}
