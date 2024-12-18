package com.server.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.app.entities.Students;
import com.server.app.service.IStudentsService;

@RestController
@RequestMapping("/api/student")
@CrossOrigin("*")
public class StudentController {

	@Autowired
	private IStudentsService studentsService;

	
	@PostMapping("/")
	public ResponseEntity<Students> addStudents(@RequestBody Students student){
		return new ResponseEntity<Students>(this.studentsService.addStudent(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Students> getStudents(@PathVariable Integer id){
		return new ResponseEntity<Students>(this.studentsService.getStudent(id),HttpStatus.OK);
	}
	
	@GetMapping("/{pageNo}/{pageSize}")
	@Cacheable( key = "#pageNo + '_' + #pageSize",value="Students")
	public ResponseEntity<Page<Students>> getAllStudents(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
		return new ResponseEntity<Page	<Students>>(this.studentsService.getAllStudent(pageNo,pageSize),HttpStatus.OK);
	}
	
	@GetMapping("/coaching/{id}")
	public ResponseEntity<List<Students>> getAllStudentsByCoachingId(@PathVariable Integer id){
		return new ResponseEntity<List<Students>>(this.studentsService.getAllByCoachingId(id),HttpStatus.OK);
	}
	
	@GetMapping("/course/{id}")
	public ResponseEntity<List<Students>> getAllStudentsByCourseId(@PathVariable Integer id){
		return new ResponseEntity<List<Students>>(this.studentsService.getAllByCourseId(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
		return new ResponseEntity<>(this.studentsService.delete(id),HttpStatus.OK);
	}
	
}
