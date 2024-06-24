package pl.wsikora.successbudget.v3.income.application.command;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.income.domain.Income;
import pl.wsikora.successbudget.v3.income.domain.IncomeRepository;

import java.math.BigDecimal;


@Service
public class IncomeService {

	private final IncomeRepository incomeRepository;

	IncomeService(IncomeRepository incomeRepository) {

		Assert.notNull(incomeRepository, "incomeRepository must not be null");

		this.incomeRepository = incomeRepository;
	}

	public void save(IncomeAttributes incomeAttributes, UserId userId) {

		Assert.notNull(incomeAttributes, "incomeAttributes attributes must not be null");
		Assert.notNull(userId, "user id must not be null");

		Income income;

		if (incomeAttributes.hasId()) {

			income = update(incomeAttributes, userId);
		}
		else {

			income = create(incomeAttributes, userId);
		}

		incomeRepository.save(income);
	}

	private Income update(IncomeAttributes incomeAttributes, UserId userId) {

		Income income = incomeRepository.getByUserId(userId);

		Money money = convertToMoney(incomeAttributes);

		income.updateInitial(money);

		return income;
	}

	private Income create(IncomeAttributes incomeAttributes, UserId userId) {

		Money money = convertToMoney(incomeAttributes);

		return new Income(userId, money);
	}

	private Money convertToMoney(IncomeAttributes incomeAttributes) {

		Currency currency = Currency.of(incomeAttributes.getInitialCurrency());
		BigDecimal amount = incomeAttributes.getInitialAmount();

		return Money.of(currency, amount);
	}
}
