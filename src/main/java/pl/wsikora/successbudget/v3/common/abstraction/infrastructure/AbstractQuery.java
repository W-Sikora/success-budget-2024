package pl.wsikora.successbudget.v3.common.abstraction.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.exception.AccessDeniedException;
import pl.wsikora.successbudget.v3.common.exception.ResourceNotFoundException;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;


public abstract class AbstractQuery<Entity extends AbstractEntityWithUserId, Dto, FilterAttributes> {

	private final Class<Entity> entityClass;

	@SuppressWarnings("unchecked")
	protected AbstractQuery() {

		this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];
	}

	public Dto getById(Long id, UserId userId) {

		Assert.notNull(id, "id must not be null");
		Assert.notNull(userId, "userId must not be null");

		Optional<Entity> optionalEntity = getJpaRepository().findById(id);

		if (optionalEntity.isEmpty()) {

			throw new ResourceNotFoundException(entityClass.getSimpleName() + " with id: " + id + " not found");
		}

		if (optionalEntity.get().hasDifferentUserId(userId)) {

			throw new AccessDeniedException("access denied - user does not have permission to access: " + entityClass.getSimpleName() + " with id: " + id);
		}

		return optionalEntity.map(this::convertToDto).get();
	}

	public Page<Dto> findAll(Pageable pageable, FilterAttributes filter, UserId userId) {

		Specification<Entity> specification = getSpecification().createSpecification(filter);

		Specification<Entity> accessSpecification = getSpecification().hasAccess(userId);

		Specification<Entity> specifications = Specification.where(specification).and(accessSpecification);

		Sort sort = getSort();

		Pageable sortedPageable = PageRequest.of(
			pageable.getPageNumber(),
			pageable.getPageSize(),
			sort
		);

		return getJpaSpecificationExecutor().findAll(specifications, sortedPageable)
			.map(this::convertToDto);
	}

	protected abstract JpaRepository<Entity, Long> getJpaRepository();

	protected abstract JpaSpecificationExecutor<Entity> getJpaSpecificationExecutor();

	protected abstract AbstractSpecification<Entity, FilterAttributes> getSpecification();

	protected abstract Dto convertToDto(Entity entity);

	protected Sort getSort() {

		return Sort.by(Sort.Direction.ASC, "id");
	}
}
