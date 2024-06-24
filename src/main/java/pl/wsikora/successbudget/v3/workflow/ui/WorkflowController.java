package pl.wsikora.successbudget.v3.workflow.ui;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;
import pl.wsikora.successbudget.v3.workflow.application.WorkflowService;


@Controller
@RequestMapping("/workflow")
class WorkflowController extends AbstractController {

	private final WorkflowService workflowService;

	WorkflowController(WorkflowService workflowService) {

		Assert.notNull(workflowService, "workflowService must not be null");

		this.workflowService = workflowService;
	}

	@GetMapping
	private String redirect(@CurrentUserId UserId userId) {

		String currentStep = workflowService.getCurrentStepUrl(userId);

		return redirect(currentStep);
	}

	@GetMapping("/previous")
	private String redirectToPreviousStep(Step currentStep) {

		String previousStepUrl = workflowService.getPreviousStepUrl(currentStep);

		return redirect(previousStepUrl);
	}

	@PostMapping("/next")
	private String saveLastStepAndRedirectToNextStep(@CurrentUserId UserId userId, Step currentStep) {

		String nextStepUrl = workflowService.saveAndGetNextStepUrl(userId, currentStep);

		return redirect(nextStepUrl);
	}
}
