package pl.wsikora.successbudget.v3.income.domain;

import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public interface IncomeRepository {

	Income getByUserId(UserId userId);

	void save(Income income);
}
