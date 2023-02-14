package laundry.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import laundry.com.response.ApiErrorResponse;

@ControllerAdvice
public class ExceptionHandling {
	
	@ExceptionHandler(value = LoginException.class)
	protected ResponseEntity<Object> handleLoginFailed(LoginException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiErrorResponse(ex, HttpStatus.UNAUTHORIZED, getRequestUri(request)), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = SystemException.class)
	protected ResponseEntity<Object> systemException(SystemException ex, WebRequest request) {
		return new ResponseEntity<>(new ApiErrorResponse(ex, HttpStatus.BAD_REQUEST, getRequestUri(request)), HttpStatus.BAD_REQUEST);
	}
	
	private String getRequestUri(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI().toString();
    }

}
