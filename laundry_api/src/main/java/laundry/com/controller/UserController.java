package laundry.com.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import laundry.com.exception.SystemException;
import laundry.com.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/laundry/users")
	public ResponseEntity<?> create(@RequestBody Map<String, String> user) throws SystemException {
		userService.insert(user);
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("timestamp", System.currentTimeMillis());
		data.put("status", HttpStatus.OK.value());
		data.put("message", "Insert customer successfully!");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
