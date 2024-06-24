package pl.wsikora.successbudget.v3.workflow.domain;

import jakarta.persistence.*;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.abstraction.domain.AbstractEntityWithUserId;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table
@Access(AccessType.FIELD)
public class Workflow extends AbstractEntityWithUserId {

	@Enumerated(EnumType.ORDINAL)
	private Step lastVisitedStep;

	private boolean completedLoop;

	private LocalDateTime dateTime;

	protected Workflow() {}

	public Workflow(UserId userId) {

		super(userId);

		this.dateTime = LocalDateTime.now();
	}

	public void updateWorkflow(Step lastStepCompleted) {

		Assert.notNull(lastStepCompleted, "lastStepCompleted must not be null");

		this.lastVisitedStep = lastStepCompleted;
		this.dateTime = LocalDateTime.now();
		this.completedLoop = lastStepCompleted.isLastStep();
	}

	public Step getLastVisitedStep() {

		return lastVisitedStep;
	}

	public LocalDateTime getDateTime() {

		return dateTime;
	}

	public boolean hasCompletedLoop() {

		return completedLoop;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {

			return true;
		}

		if (!(o instanceof Workflow workflow)) {

			return false;
		}

		if (!super.equals(o)) {

			return false;
		}

		return lastVisitedStep == workflow.lastVisitedStep
				&& Objects.equals(dateTime, workflow.dateTime);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), lastVisitedStep, dateTime);
	}
}
