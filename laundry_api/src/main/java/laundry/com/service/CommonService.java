package laundry.com.service;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CommonService {
	
	public Map<?, ?> currentUser() {
		Map<?, ?> userInfo = (Map<?, ?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userInfo;
	}

}
