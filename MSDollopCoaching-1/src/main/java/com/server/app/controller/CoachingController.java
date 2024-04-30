package com.server.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.app.entities.dto.CoachingDto;
import com.server.app.services.CoachingService;

@RestController
@RequestMapping("/api/coaching")
@RefreshScope
public class CoachingController {

	@Autowired
	private CoachingService coachingService;
	
//	@Value("${My.config.key}")
	private String pro;
	
	@PostMapping("/")
	public ResponseEntity<CoachingDto> createCoaching(@RequestBody CoachingDto coachingDto){
		return new ResponseEntity<>(this.coachingService.createCoaching(coachingDto),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CoachingDto> getById(@PathVariable Integer id){
		return new ResponseEntity<>(this.coachingService.getCoaching(id),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<String> getById(){
		return new ResponseEntity<>("Hello"+this.pro,HttpStatus.OK);
	
	}
}
