package pl.wsikora.successbudget.v3.expense.application.query;

import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.date.Date;
import pl.wsikora.successbudget.v3.common.type.description.Description;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;


public record ExpenseDto(Long id,
						 Name name,
						 Description description,
						 Date date,
						 Money amount,
						 CategoryId categoryId,
						 Name categoryName) {}