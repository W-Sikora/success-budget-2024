package pl.wsikora.successbudget.v3.budget.application.query;

public record BudgetSolution(double[] mustHaveAmounts, double[] shouldHaveAmounts, double[] couldHaveAmounts,
							 double[] wouldHaveAmounts, double[] liabilitiesAmounts, double savings) {}
