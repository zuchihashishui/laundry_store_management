package laundry.com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CommonService {
	
	public Map<String, String> currentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, String> userInfo = new HashMap<>();

		if (principal instanceof UserDetails) {
		    userInfo.put("phoneNumber", ((UserDetails) principal).getUsername());
		}
		return userInfo;
	}

}
