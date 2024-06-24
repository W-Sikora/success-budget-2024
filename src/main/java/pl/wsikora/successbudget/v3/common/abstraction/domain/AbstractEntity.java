package pl.wsikora.successbudget.v3.common.abstraction.domain;

import jakarta.persistence.*;

import java.util.Comparator;
import java.util.Objects;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity implements Comparable<AbstractEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {

		return id;
	}

	public boolean hasId() {

		return id != null;
	}

	protected AbstractEntity() {}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof AbstractEntity that)) {

			return false;
		}

		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(id);
	}

	@Override
	public int compareTo(AbstractEntity abstractEntity) {

		return this.id.compareTo(abstractEntity.id);
	}
}
