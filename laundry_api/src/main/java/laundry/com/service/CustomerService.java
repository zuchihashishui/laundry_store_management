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
public class CustomerService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insert(Map<String, String> customer) throws SystemException {
		LinkedHashMap<String, String> customerVO = sqlSession.selectOne("Customer.getCustomerByPhoneNumber", customer.get("phoneNumber"));
		if(customerVO != null) {
			throw new SystemException("Phone number has existed");
		}
		
		customer.put("password", passwordEncoder.encode(customer.get("password")));
		sqlSession.insert("Customer.insert", customer);
	}
	
	public LinkedHashMap<String, String> getCustomerByPhoneNumber(String phoneNumber) {
		LinkedHashMap<String, String> customer = sqlSession.selectOne("Customer.getCustomerByPhoneNumber", phoneNumber);
		return customer;
	}
	
	public Boolean isPhoneNumberExists(String phoneNumber) {
		LinkedHashMap<String, String> customer = sqlSession.selectOne("Customer.getCustomerByPhoneNumber", phoneNumber);
		if(customer != null) {
			return true;
		}
		return false;
	}
	
}
