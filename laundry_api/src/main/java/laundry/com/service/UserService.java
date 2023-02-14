package laundry.com.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import laundry.com.exception.SystemException;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insert(Map<String, String> user) throws SystemException {
		LinkedHashMap<String, String> userVO = sqlSession.selectOne("User.getUserByPhoneNumber", user.get("phoneNumber"));
		if(userVO != null) {
			throw new SystemException("Phone number has existed");
		}
		
		user.put("password", passwordEncoder.encode(user.get("password")));
		sqlSession.insert("User.insert", user);
	}

}
