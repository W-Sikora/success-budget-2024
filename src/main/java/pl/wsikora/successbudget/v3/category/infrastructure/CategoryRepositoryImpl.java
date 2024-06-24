package pl.wsikora.successbudget.v3.category.infrastructure;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.category.domain.Category;
import pl.wsikora.successbudget.v3.category.domain.CategoryRepository;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractRepository;


@Service
class CategoryRepositoryImpl extends AbstractRepository<Category> implements CategoryRepository {

	private final CategoryJpaRepository categoryJpaRepository;

	CategoryRepositoryImpl(CategoryJpaRepository categoryJpaRepository) {

		super(categoryJpaRepository);

		Assert.notNull(categoryJpaRepository, "objectiveJpaRepository must not be null");

		this.categoryJpaRepository = categoryJpaRepository;
	}

	@Override
	public Category getById(Long id) {

		Assert.notNull(id, "id must not be null");

		return categoryJpaRepository.getById(id);
	}
}
