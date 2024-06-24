package pl.wsikora.successbudget.v3.budget.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.budget.domain.AmountSetting;
import pl.wsikora.successbudget.v3.budget.domain.AmountSettingRepository;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;


@Service
class AmountSettingRepositoryImpl implements AmountSettingRepository {

	private final AmountSettingJpaRepository amountSettingJpaRepository;

	AmountSettingRepositoryImpl(AmountSettingJpaRepository amountSettingJpaRepository) {

		Assert.notNull(amountSettingJpaRepository, "amountSettingJpaRepository must not be null");

		this.amountSettingJpaRepository = amountSettingJpaRepository;
	}

	@Override
	public AmountSetting getByUserId(UserId userId) {

		return amountSettingJpaRepository.findByUserId(userId)
			.orElseThrow(() -> new IllegalArgumentException("no amountSetting found for userId: " + userId));
	}

	@Override
	public void save(AmountSetting amountSetting) {

		Assert.notNull(amountSetting, "amountSetting must not be null");

		amountSettingJpaRepository.save(amountSetting);
	}
}
