package pl.wsikora.successbudget.v3.category.infrastructure;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.category.application.query.CategoryDto;
import pl.wsikora.successbudget.v3.category.application.query.CategoryFilterAttributes;
import pl.wsikora.successbudget.v3.category.application.query.CategoryQuery;
import pl.wsikora.successbudget.v3.category.domain.Category;
import pl.wsikora.successbudget.v3.category.domain.Priority;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractQuery;
import pl.wsikora.successbudget.v3.common.abstraction.infrastructure.AbstractSpecification;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static pl.wsikora.successbudget.v3.category.domain.Category.LIMIT_PER_USER;


@Service
class CategoryQueryImpl extends AbstractQuery<Category, CategoryDto, CategoryFilterAttributes> implements CategoryQuery {

	private final CategoryJpaRepository categoryJpaRepository;

	CategoryQueryImpl(CategoryJpaRepository categoryJpaRepository) {

		Assert.notNull(categoryJpaRepository, "objectiveJpaRepository must not be null");

		this.categoryJpaRepository = categoryJpaRepository;
	}

	@Override
	public List<CategoryId> findAllIdsByPriorityAndUserId(Priority priority, UserId userId) {

		Assert.notNull(priority, "priority must not be null");
		Assert.notNull(userId, "userId must not be null");

		return findAllByPriorityAndUserId(priority, userId).stream()
			.sorted(comparing(CategoryDto::id))
			.map(CategoryDto::id)
			.map(CategoryId::of)
			.toList();
	}

	@Override
	public boolean hasAllRequiredPriorities(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		Set<Priority> priorities = categoryJpaRepository.getPrioritiesByUserId(userId);

		Set<Priority> allValues = Arrays.stream(Priority.values()).collect(Collectors.toSet());

		return isNotEmpty(priorities) && priorities.containsAll(allValues);
	}

	@Override
	public Optional<CategoryDto> findById(CategoryId categoryId) {

		Assert.notNull(categoryId, "categoryId must not be null");

		return categoryJpaRepository.findById(categoryId.getCategoryId())
			.map(this::convertToDto);
	}

	@Override
	public List<CategoryDto> findAllByPriorityAndUserId(Priority priority, UserId userId) {

		Assert.notNull(priority, "priority must not be null");
		Assert.notNull(userId, "userId must not be null");

		return categoryJpaRepository.findAllByPriorityAndUserId(priority, userId).stream()
			.sorted(comparing(Category::getRanking, nullsLast(naturalOrder())))
			.map(this::convertToDto)
			.toList();
	}

	@Override
	public List<CategoryDto> findAllByUserIdSortedByRanking(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return categoryJpaRepository.findAllByUserId(userId).stream()
			.sorted(comparing(Category::getRanking, nullsLast(naturalOrder())))
			.map(this::convertToDto)
			.toList();
	}

	@Override
	public List<CategoryDto> findAllByUserIdSortedById(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return categoryJpaRepository.findAllByUserId(userId).stream()
			.sorted()
			.map(this::convertToDto)
			.toList();
	}

	@Override
	public List<CategoryDto> findAllByUserIdSortedByName(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return categoryJpaRepository.findAllByUserId(userId).stream()
			.sorted(comparing(Category::getName, nullsLast(naturalOrder())))
			.map(this::convertToDto)
			.toList();
	}

	@Override
	public boolean existsByNameAndUserId(Name name, UserId userId) {

		Assert.notNull(name, "name must not be null");
		Assert.notNull(userId, "userId must not be null");

		return categoryJpaRepository.existsByNameAndUserId(name, userId);
	}

	@Override
	public boolean isUserLimitExceeded(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		return categoryJpaRepository.countAllByUserId(userId) >= LIMIT_PER_USER;
	}

	@Override
	protected JpaRepository<Category, Long> getJpaRepository() {

		return categoryJpaRepository;
	}

	@Override
	protected JpaSpecificationExecutor<Category> getJpaSpecificationExecutor() {

		return categoryJpaRepository;
	}

	@Override
	protected AbstractSpecification<Category, CategoryFilterAttributes> getSpecification() {

		return new CategorySpecification();
	}

	@Override
	protected CategoryDto convertToDto(Category category) {

		return new CategoryDto(
			category.getId(),
			category.getName(),
			category.getPriority()
		);
	}

	@Override
	protected Sort getSort() {

		return Sort.by(Sort.Direction.ASC, Name.F_NAME);
	}
}
