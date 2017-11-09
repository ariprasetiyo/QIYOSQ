package arprast.qiyosq.handle.error;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	private static final String TIMESTAMP = "timestamp";
	private static final String CAUSE = "cause";
	private static final String MESSAGE = "message";
	private static final String RESPONSE_STATUS = "responseStatus";
	private static final String RESPONSE_CODE = "responseCode";
	private static final String ERROR = "error";

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<?, ?> handle(MethodArgumentNotValidException exception) {
		FieldError aa = exception.getBindingResult().getFieldError();
		aa.getDefaultMessage();
		return error(HttpStatus.BAD_REQUEST, exception.getBindingResult().getFieldErrors().stream()
				.map(FieldError::getDefaultMessage).collect(Collectors.toList()), exception.getCause());
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<?, ?> handle(MissingServletRequestParameterException exception) {
		return error(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getCause());
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<?, ?> handle(ConstraintViolationException exception) {
		return error(HttpStatus.BAD_REQUEST, exception.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage).collect(Collectors.toList()), exception.getCause());
	}

	private Map<String, Object> error(HttpStatus httpStatus, Object message, Object cause) {
		Map<String, Object> messageErrorMap = new HashMap<String, Object>();
		messageErrorMap.put(RESPONSE_CODE, ERROR);
		messageErrorMap.put(RESPONSE_STATUS, httpStatus);
		messageErrorMap.put(CAUSE, cause);
		messageErrorMap.put(MESSAGE, message);
		messageErrorMap.put(TIMESTAMP, String.valueOf((new Date().getTime())));
		return messageErrorMap;
	}

	// @ExceptionHandler(Exception.class)
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// public final ResponseEntity<JsonMessageDto>
	// exceptionHandlerException(final Exception e) {
	// JsonMessageDto JsonMessageDto = new JsonMessageDto();
	// JsonMessageDto.setMessage(e.getMessage());
	// return new ResponseEntity<JsonMessageDto>(JsonMessageDto,
	// HttpStatus.BAD_REQUEST);
	// }
}