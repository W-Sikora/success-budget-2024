package pl.wsikora.successbudget.v3.income.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.income.application.query.IncomeDto;
import pl.wsikora.successbudget.v3.income.application.query.IncomeQuery;


@Service
class IncomeEditFormFactory {

	private final IncomeQuery incomeQuery;

	IncomeEditFormFactory(IncomeQuery incomeQuery) {

		Assert.notNull(incomeQuery, "incomeQuery must not be null");

		this.incomeQuery = incomeQuery;
	}

	IncomeEditForm createEditForm(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return incomeQuery.findByUserId(userId)
				.map(this::convertToIncomeEditForm)
				.orElseGet(IncomeEditForm::new);
	}

	private IncomeEditForm convertToIncomeEditForm(IncomeDto incomeDto) {

		Money money = incomeDto.initialAmount();

		IncomeEditForm savingEditForm = new IncomeEditForm();

		savingEditForm.setId(incomeDto.id());
		savingEditForm.setInitialCurrency(money.getCurrency().ordinal());
		savingEditForm.setInitialAmount(money.getAmount());

		return savingEditForm;
	}
}
