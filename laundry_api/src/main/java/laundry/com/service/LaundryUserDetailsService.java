package laundry.com.service;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LaundryUserDetailsService implements UserDetailsService {
	
	@Autowired
	private SqlSession sqlSession;
	
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		Map<String, String> userAuth = sqlSession.selectOne("User.getUserByPhoneNumber", phoneNumber);
		
		if(userAuth == null) {
			throw new UsernameNotFoundException(phoneNumber + " is not found!");
		}
		User user = new User(userAuth.get("phoneNumber"), userAuth.get("password"), new ArrayList<>());
		return user;
	}

}
