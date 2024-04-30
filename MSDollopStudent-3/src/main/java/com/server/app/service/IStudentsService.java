package com.server.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.server.app.entities.Students;

public interface IStudentsService {
 
	public Students addStudent(Students student);
	
	public Students updateStudent(Students student);
	
	public Students getStudent(Integer id);
	
	public Page<Students> getAllStudent(int pageNo,int pageSize);

	public List<Students> getAllByCoachingId(Integer coachingId);
	
	public List<Students> getAllByCourseId(Integer courseId);

	public ResponseEntity<?>  delete(Integer id);
	
}

