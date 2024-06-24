package pl.wsikora.successbudget.v3.liability.ui.edit;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityDto;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityQuery;

import static java.util.Objects.isNull;


@Service
class LiabilityEditFormFactory {

	private final LiabilityQuery liabilityQuery;

	LiabilityEditFormFactory(LiabilityQuery liabilityQuery) {

		Assert.notNull(liabilityQuery, "liabilityQuery must not be null");

		this.liabilityQuery = liabilityQuery;
	}

	LiabilityEditForm createEditForm(Long id, UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		if (isNull(id)) {

			return new LiabilityEditForm(userId.getValue());
		}

		LiabilityDto liabilityDto = liabilityQuery.getById(id, userId);

		return toLiabilityEditForm(liabilityDto, userId);
	}

	private LiabilityEditForm toLiabilityEditForm(LiabilityDto liabilityDto, UserId userId) {

		LiabilityEditForm liabilityEditForm = new LiabilityEditForm(userId.getValue());

		liabilityEditForm.setId(liabilityDto.id());
		liabilityEditForm.setName(liabilityDto.name().getName());
		liabilityEditForm.setDescription(liabilityDto.description().getDescription());
		liabilityEditForm.setCreditor(liabilityDto.creditor().getName());

		Money total = liabilityDto.total();
		liabilityEditForm.setTotalAmount(total.getAmount());
		liabilityEditForm.setTotalCurrency(total.getCurrency().ordinal());

		Money minimumMonthly = liabilityDto.minimumMonthly();
		liabilityEditForm.setMinimumMonthlyAmount(minimumMonthly.getAmount());
		liabilityEditForm.setMinimumMonthlyCurrency(minimumMonthly.getCurrency().ordinal());

		Money alreadyRepaid = liabilityDto.alreadyRepaid();
		liabilityEditForm.setAlreadyRepaidAmount(alreadyRepaid.getAmount());
		liabilityEditForm.setAlreadyRepaidCurrency(alreadyRepaid.getCurrency().ordinal());

		return liabilityEditForm;
	}
}
