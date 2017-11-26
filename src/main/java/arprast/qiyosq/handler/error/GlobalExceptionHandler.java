package arprast.qiyosq.handler.error;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import arprast.qiyosq.beans.Error;
import arprast.qiyosq.ref.MessageErrorType;
import arprast.qiyosq.util.LogUtil;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handle(MissingServletRequestParameterException exception) {
		return errors(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getCause());
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handle(ConstraintViolationException exception) {
		return errors(HttpStatus.BAD_REQUEST, exception.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage).collect(Collectors.toList()), exception.getCause());
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handle(IllegalStateException exception) {
		return errors(HttpStatus.BAD_REQUEST, exception.getClass(), "IllegalStateException");
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handle(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(HttpStatus.BAD_REQUEST, fieldErrors, "MethodArgumentNotValidException");
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error handle(BindException exception) {
		BindingResult result = exception.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(HttpStatus.BAD_REQUEST, fieldErrors, "BindException");
	}

	private Error processFieldErrors(HttpStatus httpStatus, List<FieldError> fieldErrors, String message) {
		Error error = new Error(httpStatus.value(), message);
		for (FieldError fieldError : fieldErrors) {
			error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		LogUtil.logDebugType(logger, true, MessageErrorType.API_REQ_RES_ERROR, error.toString());
		return error;
	}

	private Error errors(HttpStatus httpStatus, Object message, Object cause) {
		Error messageErrorMap = new Error(httpStatus.value(), message.toString());
		LogUtil.logDebugType(logger, true, MessageErrorType.API_REQ_RES_GLOBAL_ERROR, message.toString());
		return messageErrorMap;
	}
}