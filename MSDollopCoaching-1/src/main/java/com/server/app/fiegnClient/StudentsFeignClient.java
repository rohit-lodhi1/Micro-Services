package com.server.app.fiegnClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.app.entities.Students;

@FeignClient(name="STUDENT-SERVICE")
public interface StudentsFeignClient {
	@GetMapping("/api/student/course/{id}")
	public ResponseEntity<List<Students>> getAllStudentsByCourseId(@PathVariable Integer id);
	
	@GetMapping("/api/student/coaching/{id}")
	public ResponseEntity<List<Students>> getAllStudentsByCoachingId(@PathVariable Integer id);
	
}
