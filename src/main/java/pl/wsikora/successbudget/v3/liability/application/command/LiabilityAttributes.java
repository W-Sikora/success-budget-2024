package pl.wsikora.successbudget.v3.liability.application.command;

import pl.wsikora.successbudget.v3.common.abstraction.application.command.Attributable;

import java.math.BigDecimal;


public interface LiabilityAttributes extends Attributable {

	String getName();

	String getDescription();

	String getCreditor();

	BigDecimal getTotalAmount();

	Integer getTotalCurrency();

	BigDecimal getMinimumMonthlyAmount();

	Integer getMinimumMonthlyCurrency();

	BigDecimal getAlreadyRepaidAmount();

	Integer getAlreadyRepaidCurrency();
}
