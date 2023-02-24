package laundry.com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import laundry.com.config.JwtTokenProvider;
import laundry.com.exception.LoginException;
import laundry.com.response.ApiLoginResponse;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenUtil;

	@PostMapping("/api/laundry/auth/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> auth) throws LoginException {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(auth.get("phoneNumber"), auth.get("password")));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtTokenUtil.createToken(auth.get("phoneNumber"));			
			
			return new ResponseEntity<>(new ApiLoginResponse(token), HttpStatus.OK);
		} catch (Exception e) {
			throw new LoginException("Login has failed!");
		}
	}

}
