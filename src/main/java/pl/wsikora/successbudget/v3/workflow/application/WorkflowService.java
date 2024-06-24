package pl.wsikora.successbudget.v3.workflow.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.workflow.domain.InitAmountSettingEvent;
import pl.wsikora.successbudget.v3.workflow.domain.Workflow;
import pl.wsikora.successbudget.v3.workflow.domain.WorkflowRepository;


@Service
public class WorkflowService {

	private final WorkflowRepository workflowRepository;
	private final ApplicationEventPublisher eventPublisher;

	WorkflowService(WorkflowRepository workflowRepository,
					ApplicationEventPublisher eventPublisher) {

		Assert.notNull(workflowRepository, "workflowRepository must not be null");
		Assert.notNull(eventPublisher, "eventPublisher must not be null");

		this.workflowRepository = workflowRepository;
		this.eventPublisher = eventPublisher;
	}

	public String getCurrentStepUrl(UserId userId) {

		Assert.notNull(userId, "userId must not be null");

		Step step = workflowRepository.findByUserId(userId)
			.map(Workflow::getLastVisitedStep)
			.map(Step::getNextStep)
			.orElseGet(Step::getFirstStep);

		if (step == Step.AMOUNT_SETTING) {

			InitAmountSettingEvent initAmountSettingEvent = new InitAmountSettingEvent(this, userId);

			eventPublisher.publishEvent(initAmountSettingEvent);
		}

		return step.getUrl();
	}

	public String getPreviousStepUrl(Step currentStep) {

		Assert.notNull(currentStep, "currentStep must not be null");

		return currentStep.getPreviousStepUrl();
	}

	public String saveAndGetNextStepUrl(UserId userId, Step currentStep) {

		Assert.notNull(userId, "userId must not be null");
		Assert.notNull(currentStep, "currentStep must not be null");

		Workflow workflow = workflowRepository.findByUserId(userId)
			.orElseGet(() -> new Workflow(userId));

		workflow.updateWorkflow(currentStep);

		workflowRepository.save(workflow);

		Step nextStep = currentStep.getNextStep();

		if (nextStep == Step.AMOUNT_SETTING) {

			InitAmountSettingEvent initAmountSettingEvent = new InitAmountSettingEvent(this, userId);

			eventPublisher.publishEvent(initAmountSettingEvent);
		}

		return nextStep.getUrl();
	}
}
