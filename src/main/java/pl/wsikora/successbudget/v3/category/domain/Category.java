package pl.wsikora.successbudget.v3.category.domain;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Objects;


@Entity
@Table
@Access(AccessType.FIELD)
public class Category extends AbstractEntityWithUserId {

	public static final int LIMIT_PER_USER = 80;

	@Embedded
	private Name name;

	@Enumerated(EnumType.ORDINAL)
	private Priority priority;

	@Column(name = "ranking")
	private Integer ranking;

	protected Category() {}

	public Category(UserId userId, Name name, Priority priority) {

		super(userId);

		Assert.notNull(name, "name must not be null");
		Assert.notNull(priority, "priority must not be null");

		this.name = name;
		this.priority = priority;
	}

	public Name getName() {

		return name;
	}

	public void updateName(Name name) {

		Assert.notNull(name, "name must not be null");

		this.name = name;
	}

	public Priority getPriority() {

		return priority;
	}

	public void updatePriority(Priority priority) {

		Assert.notNull(priority, "priority must not be null");

		this.priority = priority;
	}

	public Integer getRanking() {

		return ranking;
	}

	public void updateRanking(Integer ranking) {

		Assert.notNull(ranking, "ranking must not be null");

		this.ranking = ranking;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof Category category)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return Objects.equals(name, category.name)
				&& priority == category.priority;
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), name, priority);
	}
}
