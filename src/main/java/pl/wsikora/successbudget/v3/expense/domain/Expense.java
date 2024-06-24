package pl.wsikora.successbudget.v3.expense.domain;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.type.categoryid.CategoryId;
import pl.wsikora.successbudget.v3.common.type.date.Date;
import pl.wsikora.successbudget.v3.common.type.description.Description;
import pl.wsikora.successbudget.v3.common.type.money.Money;
import pl.wsikora.successbudget.v3.common.type.name.Name;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.util.Objects;


@Entity
@Table(name = "expenses")
@Access(AccessType.FIELD)
public class Expense extends AbstractEntityWithUserId {

	public static final int LIMIT_PER_USER = 10_000;

	@Embedded
	private Name name;

	@Embedded
	private Description description;

	@Embedded
	private Date date;

	@Embedded
	private Money amount;

	@Embedded
	private CategoryId categoryId;

	protected Expense() {}

	public Expense(UserId userId, Name name, Description description, Date date, Money amount, CategoryId categoryId) {

		super(userId);

		Assert.notNull(name, "name must not be null");
		Assert.notNull(description, "description must not be null");
		Assert.notNull(date, "date must not be null");
		Assert.notNull(amount, "amount must not be null");
		Assert.notNull(categoryId, "categoryId must not be null");

		this.name = name;
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.categoryId = categoryId;
	}

	public Name getName() {

		return name;
	}

	public void updateName(Name name) {

		Assert.notNull(name, "name must not be null");

		this.name = name;
	}

	public Description getDescription() {

		return description;
	}

	public void updateDescription(Description description) {

		Assert.notNull(description, "description must not be null");

		this.description = description;
	}

	public Date getDate() {

		return date;
	}

	public void updateDate(Date date) {

		Assert.notNull(date, "date must not be null");

		this.date = date;
	}

	public Money getAmount() {

		return amount;
	}

	public void updateAmount(Money amount) {

		Assert.notNull(amount, "amount must not be null");

		this.amount = amount;
	}

	public CategoryId getCategoryId() {

		return categoryId;
	}

	public void updateCategoryId(CategoryId categoryId) {

		Assert.notNull(categoryId, "categoryId must not be null");

		this.categoryId = categoryId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof Expense that)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return Objects.equals(name, that.name)
				&& Objects.equals(description, that.description)
				&& Objects.equals(date, that.date)
				&& Objects.equals(amount, that.amount)
				&& Objects.equals(categoryId, that.categoryId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), name, description, date, amount, categoryId);
	}
}
