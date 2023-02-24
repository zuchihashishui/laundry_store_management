package laundry.com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import laundry.com.response.ApiResponse;
import laundry.com.service.RecommenderService;

@RestController
@Transactional
public class RecommenderController {
	
	@Autowired
	private RecommenderService recommenderService;
	
	@GetMapping("/api/laundry/recommends") 
	public ResponseEntity<?> list() {
		ApiResponse reponse = new ApiResponse(recommenderService.getRecomends());
		return new ResponseEntity<>(reponse, HttpStatus.OK);
	}

	@PostMapping("/api/laundry/recommenders")
	public ResponseEntity<?> create(@RequestBody Map<String, Object> recommender) {
		recommenderService.insert(recommender);
		
		return new ResponseEntity<>(new ApiResponse("Insert recommender successfully!"), HttpStatus.OK);
	}
	
}
