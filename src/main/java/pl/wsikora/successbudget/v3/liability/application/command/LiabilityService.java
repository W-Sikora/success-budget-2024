package pl.wsikora.successbudget.v3.liability.application.command;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.description.Description;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.liability.domain.Liability;
import pl.wsikora.successbudget.v3.liability.domain.LiabilityDefinedEvent;
import pl.wsikora.successbudget.v3.liability.domain.LiabilityRepository;


@Service
public class LiabilityService {

	private final LiabilityRepository liabilityRepository;
	private final ApplicationEventPublisher eventPublisher;

	LiabilityService(LiabilityRepository liabilityRepository,
					 ApplicationEventPublisher eventPublisher) {

		Assert.notNull(liabilityRepository, "liabilityRepository must not be null");
		Assert.notNull(eventPublisher, "eventPublisher must not be null");

		this.liabilityRepository = liabilityRepository;
		this.eventPublisher = eventPublisher;
	}

	public void save(LiabilityAttributes liabilityAttributes, UserId userId) {

		Assert.notNull(liabilityAttributes, "liabilityAttributes must not be null");
		Assert.notNull(userId, "userId must not be null");

		Liability liability;

		if (liabilityAttributes.hasId()) {

			liability = update(liabilityAttributes, userId);
		}
		else {

			liability = create(liabilityAttributes, userId);

			LiabilityDefinedEvent liabilityDefinedEvent = new LiabilityDefinedEvent(this, userId);

			eventPublisher.publishEvent(liabilityDefinedEvent);
		}

		liabilityRepository.save(liability);
	}

	private Liability update(LiabilityAttributes liabilityAttributes, UserId userId) {

		Liability liability = liabilityRepository.getByIdAndUserId(liabilityAttributes.getId(), userId);

		liability.updateName(Name.of(liabilityAttributes.getName()));
		liability.updateDescription(Description.of(liabilityAttributes.getDescription()));
		liability.updateCreditor(Name.of(liabilityAttributes.getCreditor()));

		Money total = Money.of(Currency.of(liabilityAttributes.getTotalCurrency()), liabilityAttributes.getTotalAmount());
		liability.updateTotal(total);

		Money minimumMonthly = Money.of(Currency.of(liabilityAttributes.getMinimumMonthlyCurrency()), liabilityAttributes.getMinimumMonthlyAmount());
		liability.updateMinimumMonthly(minimumMonthly);

		Money alreadyRepaid = Money.of(Currency.of(liabilityAttributes.getAlreadyRepaidCurrency()), liabilityAttributes.getAlreadyRepaidAmount());
		liability.updateAlreadyRepaid(alreadyRepaid);

		return liability;
	}

	private Liability create(LiabilityAttributes liabilityAttributes, UserId userId) {

		return new Liability(
			userId,
			Name.of(liabilityAttributes.getName()),
			Description.of(liabilityAttributes.getDescription()),
			Name.of(liabilityAttributes.getCreditor()),
			Money.of(Currency.of(liabilityAttributes.getTotalCurrency()), liabilityAttributes.getTotalAmount()),
			Money.of(Currency.of(liabilityAttributes.getMinimumMonthlyCurrency()), liabilityAttributes.getMinimumMonthlyAmount()),
			Money.of(Currency.of(liabilityAttributes.getAlreadyRepaidCurrency()), liabilityAttributes.getAlreadyRepaidAmount())
		);
	}
}
