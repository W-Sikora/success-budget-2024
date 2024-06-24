package pl.wsikora.successbudget.v3.liability.application.query;

import pl.wsikora.successbudget.v3.common.type.description.Description;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.percentage.Percentage;


public record LiabilityDto(Long id, Name name, Description description, Name creditor, Money total,
						   Money minimumMonthly, Money alreadyRepaid, Money repaid, Money totalRepaid,
						   Money remainingToBeRepaid, Percentage percentage) {}
