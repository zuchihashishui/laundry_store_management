package laundry.com.controller;

import java.util.LinkedHashMap;
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

import laundry.com.config.JwtTokenUtil;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/laundry/auth/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> auth) {
		try { 
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(auth.get("phoneNumber"), auth.get("password")));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtTokenUtil.createToken(auth.get("phoneNumber"));
			
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("status", HttpStatus.OK.value());
			data.put("access_token", token);
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("status", HttpStatus.UNAUTHORIZED.value());
			data.put("message", e.getMessage());
			return new ResponseEntity<>(data, HttpStatus.UNAUTHORIZED);
		}
	}

}
