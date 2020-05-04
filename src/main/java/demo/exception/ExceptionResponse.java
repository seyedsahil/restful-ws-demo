package demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private Date timestamp;

	private HttpStatus status;

	private Map<String, String> errors;

	public ExceptionResponse(HttpStatus status, Map<String, String> errors) {
		super();
		this.timestamp = new Date();
		this.status = status;
		this.errors = errors;
	}

	public ExceptionResponse(HttpStatus status, String message) {
		super();
		this.timestamp = new Date();
		this.status = status;
		this.errors = new HashMap<String, String>();
		errors.put("message", message);
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
