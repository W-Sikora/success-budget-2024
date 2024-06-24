package pl.wsikora.successbudget.v3.income.application.query;

import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Optional;


public interface IncomeQuery {

	Optional<IncomeDto> findByUserId(UserId userId);
}
