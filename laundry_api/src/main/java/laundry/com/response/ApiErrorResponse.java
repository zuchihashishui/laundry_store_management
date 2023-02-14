package laundry.com.response;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse extends LinkedHashMap<String, Object> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiErrorResponse(Exception exception, HttpStatus httpStatus, String path) {
        this.put("timestamp", System.currentTimeMillis());
        this.put("status", httpStatus.value());
        this.put("error", exception.getMessage());
        this.put("errorType", exception.getClass().getSimpleName());
        this.put("path", path);
    }
	
	public ApiErrorResponse(Exception exception, String errorType, HttpStatus httpStatus, String path) {
        this.put("timestamp", System.currentTimeMillis());
        this.put("status", httpStatus.value());
        this.put("error", exception.getMessage());
        this.put("errorType", errorType);
        this.put("path", path);
    }
	
	public ApiErrorResponse(String error, HttpStatus httpStatus, String path) {
        this.put("timestamp", System.currentTimeMillis());
        this.put("status", httpStatus.value());
        this.put("error", error);
        this.put("path", path);
    }
	
	public ApiErrorResponse(String path) {
        this.put("timestamp", System.currentTimeMillis());
        this.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.put("error", "INTERNAL ERROR");
        this.put("path", path);
    }

}
