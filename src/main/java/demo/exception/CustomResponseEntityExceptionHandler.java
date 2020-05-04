package demo.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = exception.getBindingResult().getFieldErrors().stream().map(fieldError -> {
			String[] error = new String[2];
			error[0] = fieldError.getField();
			error[1] = fieldError.getDefaultMessage();
			return error;
		}).collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
		errors.putAll(exception.getBindingResult().getGlobalErrors().stream().map(globalError -> {
			String[] error = new String[2];
			error[0] = "errors";
			error[1] = globalError.getDefaultMessage();
			return error;
		}).collect(Collectors.toMap(entry -> entry[0], entry -> entry[1])));
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, errors);
		return new ResponseEntity<Object>(exceptionResponse, exceptionResponse.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST,
				"No data present in the request.");
		return new ResponseEntity<Object>(exceptionResponse, exceptionResponse.getStatus());
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
	}

}
