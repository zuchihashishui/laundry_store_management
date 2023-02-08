package laundry.com.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import laundry.com.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/laundry/customers")
	public ResponseEntity<?> create(@RequestBody Map<String, String> customer) {
		customerService.insert(customer);
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("timestamp", System.currentTimeMillis());
		data.put("status", HttpStatus.OK.value());
		data.put("message", "Insert customer successfully!");
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/laundry/customers/{phoneNumber}")
	public ResponseEntity<?> detail(String phoneNumber) {
		LinkedHashMap<String, String> customer = customerService.getCustomerByPhoneNumber(phoneNumber);
		
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("timestamp", System.currentTimeMillis());
		data.put("status", HttpStatus.OK.value());
		data.put("data", customer);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("/laundry/customers/{phoneNumber}/check-phone-number")
	public ResponseEntity<?> checkPhoneNumber(String phoneNumber) {
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("timestamp", System.currentTimeMillis());
		data.put("status", HttpStatus.OK.value());
		data.put("data", customerService.isPhoneNumberExists(phoneNumber));
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
