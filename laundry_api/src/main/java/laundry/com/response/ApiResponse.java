package laundry.com.response;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;

import laundry.com.common.Utils;

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
		this.put("path", Utils.getRequestUri());
	}
	
	public ApiResponse(Object data, String message) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put("data", data);
		this.put("message", message);
		this.put("path", Utils.getRequestUri());
	}
	
	public ApiResponse(String message) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put("message", message);
		this.put("path", Utils.getRequestUri());
	}

}
