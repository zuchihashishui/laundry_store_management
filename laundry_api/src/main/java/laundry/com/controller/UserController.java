package laundry.com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import laundry.com.exception.SystemException;
import laundry.com.response.ApiResponse;
import laundry.com.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/laundry/users")
	public ResponseEntity<?> create(@RequestBody Map<String, String> user) throws SystemException {
		userService.insert(user);
		
		return new ResponseEntity<>(new ApiResponse("Regist user successfully!"), HttpStatus.OK);
	}

}
