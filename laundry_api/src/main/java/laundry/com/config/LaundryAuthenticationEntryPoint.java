package laundry.com.config;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LaundryAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws BadCredentialsException, JsonProcessingException, IOException {
		httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("timestamp", System.currentTimeMillis());
		error.put("status", HttpStatus.UNAUTHORIZED.value());
		error.put("message", HttpStatus.UNAUTHORIZED);
		error.put("path", httpServletRequest.getServletPath());
		
		httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(error));
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON.toString());
		httpServletResponse.flushBuffer();
	}
}
