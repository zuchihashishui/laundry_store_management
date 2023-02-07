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
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map<String, Object> userInfo = sqlSession.selectOne("UserInfo.getDetailedUserInfoByUsername", username);
		if(userInfo == null) {
			throw new UsernameNotFoundException(username + " is not found!");
		}
		User user = new User(userInfo.get("username").toString(), userInfo.get("password").toString(), new ArrayList<>());
		return user;
	}

}
