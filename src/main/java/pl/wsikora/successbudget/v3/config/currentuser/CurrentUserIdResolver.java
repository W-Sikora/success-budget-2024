package pl.wsikora.successbudget.v3.config.currentuser;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import pl.wsikora.successbudget.v3.common.util.CurrentUserId;


class CurrentUserIdResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		return parameter.getParameterAnnotation(CurrentUserId.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (isInvalid(authentication)) {

			throw new RuntimeException();
		}

		CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();

		return currentUser.getUserId();
	}

	private boolean isInvalid(Authentication authentication) {

		return authentication == null || authentication instanceof AnonymousAuthenticationToken;
	}
}
