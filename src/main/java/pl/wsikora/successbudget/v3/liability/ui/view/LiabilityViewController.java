package pl.wsikora.successbudget.v3.liability.ui.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.type.step.Step;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityDto;
import pl.wsikora.successbudget.v3.liability.application.query.LiabilityQuery;
import pl.wsikora.successbudget.v3.liability.domain.Liability;


@Controller
@RequestMapping("/liability")
class LiabilityViewController extends AbstractController {

	private final LiabilityQuery liabilityQuery;

	LiabilityViewController(LiabilityQuery liabilityQuery) {

		Assert.notNull(liabilityQuery, "liabilityQuery must not be null");

		this.liabilityQuery = liabilityQuery;
	}

	@GetMapping
	private String view() {

		return "liability/liability-view";
	}

	@ModelAttribute
	private void model(Model model,
					   @CurrentUserId UserId userId,
					   @PageableDefault(size = 20) Pageable pageable,
					   LiabilityFilterForm liabilityFilterForm) {

		Page<LiabilityDto> page = liabilityQuery.findAll(pageable, liabilityFilterForm, userId);

		model.addAttribute(PAGE, page);
		model.addAttribute(CURRENT_STEP, Step.LIABILITY);
		model.addAttribute(ADD_BUTTON_URL, "/liability/edit");
		model.addAttribute(ADD_BUTTON_DISABLED, liabilityQuery.isUserLimitExceeded(userId));
		model.addAttribute(LIMIT, Liability.LIMIT_PER_USER);
		model.addAttribute("liabilityCollectiveDto", liabilityQuery.getLiabilityCollectiveDto(userId));
	}
}
