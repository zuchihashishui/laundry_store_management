package laundry.com.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
	
	@ExceptionHandler(value = LoginException.class)
	protected ResponseEntity<?> handleLoginFailed(LoginException exception) {
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("timestamp", System.currentTimeMillis());
		error.put("status", HttpStatus.UNAUTHORIZED.value());
		error.put("error", exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

}
