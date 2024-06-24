package pl.wsikora.successbudget.v3.budget.application.query;

import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public interface AmountSettingQuery {

	AmountSettingDto getAmountSettingDto(UserId userId);
}
