package pl.wsikora.successbudget.v3.liability.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractRepository;
import pl.wsikora.successbudget.v3.liability.domain.Liability;
import pl.wsikora.successbudget.v3.liability.domain.LiabilityRepository;


@Service
class LiabilityRepositoryImpl extends AbstractRepository<Liability> implements LiabilityRepository {

	private final LiabilityJpaRepository liabilityJpaRepository;

	LiabilityRepositoryImpl(LiabilityJpaRepository liabilityJpaRepository) {

		super(liabilityJpaRepository);

		Assert.notNull(liabilityJpaRepository, "liabilityJpaRepository must not be null");

		this.liabilityJpaRepository = liabilityJpaRepository;
	}

	@Override
	public Liability getById(Long id) {

		Assert.notNull(id, "id must not be null");

		return liabilityJpaRepository.getById(id);
	}
}
