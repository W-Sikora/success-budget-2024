package pl.wsikora.successbudget.v3.budget.domain;

import pl.wsikora.successbudget.v3.common.type.userid.UserId;


public interface AmountSettingRepository {

	AmountSetting getByUserId(UserId userId);

	void save(AmountSetting amountSetting);
}
