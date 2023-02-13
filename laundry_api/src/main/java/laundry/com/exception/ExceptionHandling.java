package laundry.com.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandling {
	
	@ExceptionHandler(value = LoginException.class)
	protected ResponseEntity<Object> handleLoginFailed(LoginException ex, WebRequest request) {
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("timestamp", System.currentTimeMillis());
		error.put("status", HttpStatus.UNAUTHORIZED.value());
		error.put("error", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = {AccessDeniedException.class})
	protected ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("timestamp", System.currentTimeMillis());
		error.put("status", HttpStatus.FORBIDDEN.value());
		error.put("error", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = SystemException.class)
	protected ResponseEntity<Object> systemException(SystemException ex, WebRequest request) {
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("timestamp", System.currentTimeMillis());
		error.put("status", HttpStatus.BAD_REQUEST.value());
		error.put("error", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
