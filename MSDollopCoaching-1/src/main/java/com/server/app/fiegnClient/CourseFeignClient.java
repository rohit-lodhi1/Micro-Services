package com.server.app.fiegnClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.server.app.entities.dto.CourseDto;

@FeignClient(name="COURSE-SERVICE")
public interface CourseFeignClient {
	
	@GetMapping("/api/course/coaching/{id}")
	public ResponseEntity<List<CourseDto>> getCourseOfCoaching(@PathVariable Integer id);
	
}
