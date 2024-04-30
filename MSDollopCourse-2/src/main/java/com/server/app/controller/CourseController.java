package com.server.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.app.entities.dto.CourseDto;
import com.server.app.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/")
	public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){

		return new ResponseEntity<CourseDto>(this.courseService.createCourse(courseDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseDto> getCourse(@PathVariable Integer id){
		return new ResponseEntity<CourseDto>(this.courseService.getCourse(id),HttpStatus.OK);
	}
	
	@GetMapping("/coaching/{id}")
	public ResponseEntity<List<CourseDto>> getCourseOfCoaching(@PathVariable Integer id){
		return new ResponseEntity<List<CourseDto>>(this.courseService.getCoursesOfCoaching(id),HttpStatus.OK);
	}
}
