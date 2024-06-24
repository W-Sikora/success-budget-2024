package pl.wsikora.successbudget.v3.common.abstraction.infrastructure;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Arrays;
import java.util.Objects;


public abstract class AbstractSpecification<Entity, FilterAttributes> {

	private final Specification<Entity> ENTITY_SPECIFICATION = Specification.where(null);

	@SafeVarargs
	protected final Specification<Entity> combineSpecifications(Specification<Entity>... specifications) {

		Assert.notNull(specifications, "specifications must not be null");

		return Arrays.stream(specifications)
				.filter(Objects::nonNull)
				.reduce(ENTITY_SPECIFICATION, Specification::and);
	}

	protected abstract Specification<Entity> createSpecification(FilterAttributes filterAttributes);

	protected abstract Specification<Entity> hasAccess(UserId userId);
}
