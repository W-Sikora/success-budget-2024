package pl.wsikora.successbudget.v3.budget.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.budget.application.query.AmountSettingDto;
import pl.wsikora.successbudget.v3.budget.application.query.AmountSettingQuery;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;


@Controller
@RequestMapping("/amount-setting")
class AmountSettingController extends AbstractController {

	private static final String VIEW = "budget/amount-setting-view";

	private final AmountSettingQuery amountSettingQuery;

	AmountSettingController(AmountSettingQuery amountSettingQuery) {

		Assert.notNull(amountSettingQuery, "amountSettingQuery must not be null");

		this.amountSettingQuery = amountSettingQuery;
	}

	@GetMapping
	private String view(@CurrentUserId UserId userId, Model model) {

		AmountSettingDto amountSettingDto = amountSettingQuery.getAmountSettingDto(userId);

		model.addAttribute(CURRENT_STEP, Step.AMOUNT_SETTING);
		model.addAttribute("amountSetting", amountSettingDto);
		model.addAttribute(HIDE_MIDDLE_BUTTON, true);
		model.addAttribute(NEXT_BUTTON_DISABLED, !amountSettingDto.balanced());

		return VIEW;
	}
}
