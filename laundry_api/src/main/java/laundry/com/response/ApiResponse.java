package laundry.com.response;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ApiResponse extends LinkedHashMap<String, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiResponse(Object data) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put("data", data);
		this.put("message", "success");
		this.put("path", getRequestUri());
	}
	
	public ApiResponse(String key, Object data) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put(key, data);
		this.put("message", "success");
		this.put("path", getRequestUri());
	}
	
	public ApiResponse(String key, String data) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put(key, data);
		this.put("message", "success");
		this.put("path", getRequestUri());
	}
	
	public ApiResponse(String key, Object data, String message) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put(key, data);
		this.put("message", message);
		this.put("path", getRequestUri());
	}
	
	public ApiResponse(Object data, String message) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put("data", data);
		this.put("message", message);
		this.put("path", getRequestUri());
	}
	
	public ApiResponse(String message) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put("message", message);
		this.put("path", getRequestUri());
	}
	
	private String getRequestUri() {
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
	    ServletRequestAttributes servlReqAttr = (ServletRequestAttributes)reqAttr;
	    HttpServletRequest req = servlReqAttr.getRequest();
	    return req.getRequestURI().toString();
	}

}
