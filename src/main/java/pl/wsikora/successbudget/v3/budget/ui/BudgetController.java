package pl.wsikora.successbudget.v3.budget.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.budget.application.query.*;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;

import java.math.BigDecimal;
import java.util.List;


@Controller
@RequestMapping("/budget")
class BudgetController extends AbstractController {

	private final WeightsDataProvider weightsDataProvider;
	private final AmountSettingQuery amountSettingQuery;
	private final BudgetOptimizationService budgetOptimizationService;

	BudgetController(WeightsDataProvider weightsDataProvider,
					 AmountSettingQuery amountSettingQuery,
					 BudgetOptimizationService budgetOptimizationService) {

		Assert.notNull(weightsDataProvider, "weightsDataProvider must not be null");
		Assert.notNull(amountSettingQuery, "amountSettingQuery must not be null");
		Assert.notNull(budgetOptimizationService, "budgetOptimizationService must not be null");

		this.weightsDataProvider = weightsDataProvider;
		this.amountSettingQuery = amountSettingQuery;
		this.budgetOptimizationService = budgetOptimizationService;
	}

	@GetMapping
	private String view(@CurrentUserId UserId userId, Model model) {

		model.addAttribute(NEXT_BUTTON_DISABLED, true);
		model.addAttribute(HIDE_MIDDLE_BUTTON, true);
		model.addAttribute(CURRENT_STEP, Step.BUDGET);

		WeightDto weightDto = weightsDataProvider.getWeights(userId);
		AmountSettingDto amountSettingDto = amountSettingQuery.getAmountSettingDto(userId);

		double disposableIncome = amountSettingDto.disposableIncome().doubleValue();
		double[] mustHaveMin = getAsArray(amountSettingDto.minimumAllowableMustExpenseAmounts());
		double maxShouldSum = amountSettingDto.maximumAllowableAmountOfShouldExpense().doubleValue();
		double maxCouldSum = amountSettingDto.maximumAllowableAmountOfCouldExpense().doubleValue();
		double maxWouldSum = amountSettingDto.maximumAllowableAmountOfWouldExpense().doubleValue();
		double[] liabilitiesMin = getAsArray(amountSettingDto.minimumAllowableLiabilityAmounts());
		double[] liabilitiesMax = getAsArray(amountSettingDto.maximumAllowableLiabilityAmounts());

		BudgetSolution budgetSolution = budgetOptimizationService.orToolsOptimizeBudget(
			weightDto.getShouldHaveWeights(),
			weightDto.getCouldHaveWeights(),
			weightDto.getWouldHaveWeights(),
			weightDto.getAlphaWeights(),
			disposableIncome,
			mustHaveMin,
			maxShouldSum,
			maxCouldSum,
			maxWouldSum,
			liabilitiesMin,
			liabilitiesMax
		);

		model.addAttribute("budgetSolution", budgetSolution);
		model.addAttribute("amountSetting", amountSettingDto);

		return "/budget/budget-view";
	}

	private static double[] getAsArray(List<BigDecimal> bigDecimals) {

		return bigDecimals.stream()
			.mapToDouble(BigDecimal::doubleValue)
			.toArray();
	}
}
