package laundry.com.response;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;

import laundry.com.common.Utils;

public class ApiLoginResponse extends LinkedHashMap<String, Object> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiLoginResponse(String token) {
		this.put("timestamp", System.currentTimeMillis());
		this.put("status", HttpStatus.OK.value());
		this.put("token", token);
		this.put("message", "success");
		this.put("path", Utils.getRequestUri());
	}

}
