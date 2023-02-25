package laundry.com.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import laundry.com.response.ApiResponse;
import laundry.com.service.StoreService;

@RestController
public class StoreController {
	
	@Autowired
	private StoreService storeService;

	@GetMapping("/api/laundry/stores/{storeId}")
	public ResponseEntity<?> getStoreBySstoreId(@PathVariable Integer storeId) {
		LinkedHashMap<String, Object> store = storeService.getStoreByStoreId(storeId);
		ApiResponse response = new ApiResponse(store);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/api/laundry/stores")
	public ResponseEntity<?> insert(@RequestBody Map<String, Object> store) {
		storeService.insert(store);
		return new ResponseEntity<>(new ApiResponse("Insert Store Successfully!"), HttpStatus.OK);
	}
	
	
	@PutMapping("/api/laundry/stores/{storeId}")
	public ResponseEntity<?> update(@RequestBody Map<String, Object> store, @PathVariable Integer storeId) {
		storeService.update(store);
		return new ResponseEntity<>(new ApiResponse("Update Store Successfully!"), HttpStatus.OK);
	}
	
	@DeleteMapping("/api/laundry/stores/{storeId}")
	public ResponseEntity<?> delete(@PathVariable Integer storeId) {
		storeService.delete(storeId);
		return new ResponseEntity<>(new ApiResponse("Delete Store Successfully!"), HttpStatus.OK);
	}
}
