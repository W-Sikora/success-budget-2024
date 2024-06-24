package pl.wsikora.successbudget.v3.liability.infrastructure;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractQuery;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractSpecification;
import pl.wsikora.successbudget.v3.common.type.currency.Currency;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityCollectiveDto;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityDto;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityFilterAttributes;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityQuery;
import pl.wsikora.successbudget.v3.liability.domain.Liability;

import java.util.List;

import static pl.wsikora.successbudget.v3.liability.domain.Liability.LIMIT_PER_USER;


@Service
class LiabilityQueryImpl extends AbstractQuery<Liability, LiabilityDto, LiabilityFilterAttributes> implements LiabilityQuery {

	private final LiabilityJpaRepository liabilityJpaRepository;

	LiabilityQueryImpl(LiabilityJpaRepository liabilityJpaRepository) {

		Assert.notNull(liabilityJpaRepository, "liabilityJpaRepository must not be null");

		this.liabilityJpaRepository = liabilityJpaRepository;
	}

	@Override
	protected JpaRepository<Liability, Long> getJpaRepository() {

		return liabilityJpaRepository;
	}

	@Override
	protected JpaSpecificationExecutor<Liability> getJpaSpecificationExecutor() {

		return liabilityJpaRepository;
	}

	@Override
	protected AbstractSpecification<Liability, LiabilityFilterAttributes> getSpecification() {

		return new LiabilitySpecification();
	}

	@Override
	protected LiabilityDto convertToDto(Liability entity) {

		return new LiabilityDto(
				entity.getId(),
				entity.getName(),
				entity.getDescription(),
				entity.getCreditor(),
				entity.getTotal(),
				entity.getMinimumMonthly(),
				entity.getAlreadyRepaid(),
				entity.getRepaid(),
				entity.getTotalRepaid(),
				entity.getRemainingToBeRepaid(),
				entity.getTotalRepaid().percentageOf(entity.getTotal())
		);
	}

	@Override
	protected Sort getSort() {

		return Sort.by(Sort.Direction.ASC, Name.F_NAME);
	}

	@Override
	public LiabilityCollectiveDto getLiabilityCollectiveDto(UserId userId) {

		List<Liability> liabilities = liabilityJpaRepository.findAllByUserId(userId);

		if (liabilities.isEmpty()) {

			return null;
		}

		Currency currency = liabilities.stream()
				.findFirst()
				.orElseThrow()
				.getTotal()
				.getCurrency();

		Money total = liabilities.stream()
				.map(Liability::getTotal)
				.reduce(Money.of(currency), Money::add);

		Money totalRepaid = liabilities.stream()
				.map(Liability::getTotalRepaid)
				.reduce(Money.of(currency), Money::add);

		Money remainingToBeRepaid = liabilities.stream()
				.map(Liability::getRemainingToBeRepaid)
				.reduce(Money.of(currency), Money::add);

		return new LiabilityCollectiveDto(
				total,
				totalRepaid,
				remainingToBeRepaid
		);
	}

	@Override
	public List<LiabilityDto> findAllByUserId(UserId userId) {

		return liabilityJpaRepository.findAllByUserId(userId).stream()
			.sorted()
			.map(this::convertToDto)
			.toList();
	}

	@Override
	public boolean existsByNameAndUserId(Name name, UserId userId) {

		Assert.notNull(name, "name must not be null");
		Assert.notNull(userId, "userId must not be null");

		return liabilityJpaRepository.existsByNameAndUserId(name, userId);
	}

	@Override
	public boolean isUserLimitExceeded(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return liabilityJpaRepository.countAllByUserId(userId) >= LIMIT_PER_USER;
	}
}
