package pl.wsikora.successbudget.v3.liability.ui.edit;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.wsikora.successbudget.v3.common.type.userid.UserId;
import pl.wsikora.successbudget.v3.common.abstraction.ui.AbstractController;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;
import pl.wsikora.successbudget.v3.liability.application.command.LiabilityService;


@Controller
@RequestMapping("/liability/edit")
class LiabilityEditController extends AbstractController {

	private static final String VIEW = "/liability/liability-edit-view";

	private static final String LIABILITY_EDIT_FORM = "liabilityEditForm";

	private final LiabilityService liabilityService;
	private final LiabilityEditFormValidator liabilityEditFormValidator;
	private final LiabilityEditFormFactory liabilityEditFormFactory;

	LiabilityEditController(LiabilityService liabilityService,
							LiabilityEditFormValidator liabilityEditFormValidator,
							LiabilityEditFormFactory liabilityEditFormFactory) {

		Assert.notNull(liabilityService, "liabilityService must not be null");
		Assert.notNull(liabilityEditFormValidator, "liabilityEditFormValidator must not be null");
		Assert.notNull(liabilityEditFormFactory, "liabilityEditFormFactory must not be null");

		this.liabilityService = liabilityService;
		this.liabilityEditFormValidator = liabilityEditFormValidator;
		this.liabilityEditFormFactory = liabilityEditFormFactory;
	}

	@GetMapping
	private String view() {

		return VIEW;
	}

	@PostMapping
	private String save(@ModelAttribute(LIABILITY_EDIT_FORM) LiabilityEditForm liabilityEditForm,
						@CurrentUserId UserId userId, Errors errors) {

		liabilityEditFormValidator.validate(liabilityEditForm, errors);

		if (errors.hasErrors()) {

			return VIEW;
		}

		liabilityService.save(liabilityEditForm, userId);

		return redirect("/liability");
	}

	@ModelAttribute(LIABILITY_EDIT_FORM)
	private LiabilityEditForm savingEditForm(@RequestParam(required = false) Long id, @CurrentUserId UserId userId) {

		return liabilityEditFormFactory.createEditForm(id, userId);
	}
}
