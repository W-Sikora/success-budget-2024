package pl.wsikora.successbudget.v3.exception.ui;

import jakarta.annotation.Nullable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wsikora.successbudget.v3.common.exception.AccessDeniedException;
import pl.wsikora.successbudget.v3.common.exception.ResourceNotFoundException;

import java.util.Optional;


@Controller
@RequestMapping("/exception")
class GlobalExceptionHandlerController implements ErrorController {

	@GetMapping
	private String view() {

		return "exception/exception-view";
	}

	@ExceptionHandler(Exception.class)
	@ModelAttribute
	private void initData(HttpServletRequest request, Model model) {

		Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

		setMessage(model, throwable);

		setStatusCode(request, model);
	}

	@ExceptionHandler({AccessDeniedException.class, ResourceNotFoundException.class})
	@ModelAttribute
	private void initDataForCustomException(HttpServletRequest request, Model model) {

		Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

		setMessage(model, throwable);

		if (throwable.getCause() instanceof AccessDeniedException) {

			setStatusCode(model, 403);
		} else if (throwable.getCause() instanceof ResourceNotFoundException) {

			setStatusCode(model, 404);
		}
	}

	private void setMessage(Model model, Throwable throwable) {

		model.addAttribute("message", getMessage(throwable));
	}

	private void setStatusCode(HttpServletRequest request, Model model) {

		int statusCode = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		setStatusCode(model, statusCode);
	}

	private void setStatusCode(Model model, int statusCode) {

		model.addAttribute("statusCode", statusCode);
	}

	private String getMessage(@Nullable Throwable throwable) {

		return Optional.ofNullable(throwable)
			.map(Throwable::getMessage)
			.filter(StringUtils::isNotEmpty)
			.orElse(StringUtils.EMPTY);
	}
}
