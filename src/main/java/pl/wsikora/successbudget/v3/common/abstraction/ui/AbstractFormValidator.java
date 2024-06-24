package pl.wsikora.successbudget.v3.common.abstraction.ui;

import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.wsikora.successbudget.v3.common.util.ValidatorErrorCode;

import java.lang.reflect.ParameterizedType;


public abstract class AbstractFormValidator<EditForm> implements Validator, ValidatorErrorCode {

	public static final String E_HAS_ASSIGNED_CATEGORY = "category.is.already.assigned";

	private final Class<EditForm> editFormClass;

	@SuppressWarnings("unchecked")
	protected AbstractFormValidator() {

		this.editFormClass = (Class<EditForm>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public abstract void validateForm(EditForm editForm, Errors errors);

	@Override
	public boolean supports(Class<?> clazz) {

		return editFormClass.isAssignableFrom(clazz);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void validate(Object target, Errors errors) {

		Assert.notNull(target, "target must not be null");
		Assert.notNull(errors, "errors must not be null");

		validateForm((EditForm) target, errors);
	}
}
