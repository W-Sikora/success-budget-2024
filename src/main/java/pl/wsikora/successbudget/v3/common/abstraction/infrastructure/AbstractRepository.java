package pl.wsikora.successbudget.v3.common.abstraction.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.exception.AccessDeniedException;
import pl.wsikora.successbudget.v3.common.exception.ResourceNotFoundException;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;


public abstract class AbstractRepository<Entity extends AbstractEntityWithUserId> {

	private final Class<Entity> entityClass;
	private final JpaRepository<Entity, Long> jpaRepository;

	@SuppressWarnings("unchecked")
	protected AbstractRepository(JpaRepository<Entity, Long> jpaRepository) {

		this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		this.jpaRepository = jpaRepository;
	}

	public Entity getByIdAndUserId(Long id, UserId userId) {

		Assert.notNull(id, "id must not be null");
		Assert.notNull(userId, "userId must not be null");

		Optional<Entity> optionalEntity = jpaRepository.findById(id);

		if (optionalEntity.isEmpty()) {

			throw new ResourceNotFoundException(entityClass.getSimpleName() + " with id: " + id + " not found");
		}

		if (optionalEntity.get().hasDifferentUserId(userId)) {

			throw new AccessDeniedException("access denied - user does not have permission to access: " + entityClass.getSimpleName() + " with id: " + id);
		}

		return optionalEntity.get();
	}

	public void save(Entity entity) {

		Assert.notNull(entity, entityClass.getSimpleName() + " must not be null");

		jpaRepository.save(entity);
	}
}
